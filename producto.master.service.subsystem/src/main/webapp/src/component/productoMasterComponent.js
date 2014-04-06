define(['controller/selectionController', 'model/cacheModel', 'model/productoMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/productoComponent',
 'component/itemComponent', 'component/dependenciaComponent', 'delegate/productoMasterDelegate' 
 
 ],function(SelectionController, CacheModel, ProductoMasterModel, CRUDComponent, TabController, ProductoComponent,
 ItemComponent
 ,
 DependenciaComponent
 ) {
    App.Component.ProductoMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('productoMaster');
            var uComponent = new ProductoComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-producto-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-producto-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-producto-list', function() {
                self.hideChilds();
            });
            Backbone.on('producto-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'producto-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-producto-list', function(params) {
                self.list(params,uComponent);
            });
            Backbone.on(uComponent.componentId + '-instead-producto-save', function(params) {
                self.model.set('productoEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var itemModels = self.itemComponent.componentController.itemModelList;
                self.model.set('listItem', []);
                self.model.set('createItem', []);
                self.model.set('updateItem', []);
                self.model.set('deleteItem', []);
                for (var i = 0; i < itemModels.models.length; i++) {
                    var m = itemModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createItem').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateItem').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < itemModels.deletedModels.length; i++) {
                    var m = itemModels.deletedModels[i];
                    self.model.get('deleteItem').push(m.toJSON());
                }
                var dependenciaModels = self.dependenciaComponent.componentController.dependenciaModelList;
                self.model.set('listDependencia', []);
                self.model.set('createDependencia', []);
                self.model.set('updateDependencia', []);
                self.model.set('deleteDependencia', []);
                for (var i = 0; i < dependenciaModels.models.length; i++) {
                    var m = dependenciaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createDependencia').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateDependencia').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < dependenciaModels.deletedModels.length; i++) {
                    var m = dependenciaModels.deletedModels[i];
                    self.model.get('deleteDependencia').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Item", name: "item", enable: true},
                            ,
                            {label: "Dependencia", name: "dependencia", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.ProductoMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.itemComponent = new ItemComponent();
                    self.itemModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ItemModel), self.model.get('listItem'));
                    self.itemComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ItemModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ItemModel, App.Model.ItemList, self.itemModels)
                    });
                    self.itemComponent.render(self.tabs.getTabHtmlId('item'));
                    Backbone.on(self.itemComponent.componentId + '-post-item-create', function(params) {
                        params.view.currentItemModel.setCacheList(params.view.itemModelList);
                    });
					self.dependenciaComponent = new DependenciaComponent();
                    self.dependenciaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.DependenciaModel), self.model.get('listDependencia'));
                    self.dependenciaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.DependenciaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.DependenciaModel, App.Model.DependenciaList, self.dependenciaModels)
                    });
                    self.dependenciaComponent.render(self.tabs.getTabHtmlId('dependencia'));
                    Backbone.on(self.dependenciaComponent.componentId + '-post-dependencia-create', function(params) {
                        params.view.currentDependenciaModel.setCacheList(params.view.dependenciaModelList);
                    });
                    self.itemToolbarModel = self.itemComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.itemComponent.setToolbarModel(self.itemToolbarModel);                    
                    self.dependenciaToolbarModel = self.dependenciaComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.dependenciaComponent.setToolbarModel(self.dependenciaToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.ProductoMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.ProductoMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        },
        getAmmountProduct: function(id, callback, callbackError) {
            console.log('getAmmountProduct: ' + id);
            $.ajax({
                url: '/producto.master.service.subsystem/webresources/ProductoMaster/'+id+'/getAmmountProduct',
                type: 'GET',
                data: {},
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        },
        list: function(params,uComponent) {

            if (params) {
                var data = params.data;
            }
            //oculta los hijos
            this.hideChilds();
            var self = uComponent.componentController;
            if (!self.productoModelList) {
                self.productoModelList = new self.listModelClass();
            }
            //consulta la lista de usuarios
            
            self.productoModelList.fetch({
                data: data,
                success: function() {
                    
                     _.each(self.productoModelList.models,function(element){
                      App.Delegate.ProductoMasterDelegate.getAmmountProduct(element.id,function (data){
                            element.set({cantidadDisponible: data});
                        },function(){
                            console.log("Error");
                        });
                    });
                    self._renderList();
                },
                error: function(mode, error) {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-list', view: self, error: error});
                }
            });
        }

    });

    return App.Component.ProductoMasterComponent;
});