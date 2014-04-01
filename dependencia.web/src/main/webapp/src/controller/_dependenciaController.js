define(['model/dependenciaModel'], function(dependenciaModel) {
    App.Controller._DependenciaController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#dependencia').html());
            this.listTemplate = _.template($('#dependenciaList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'dependencia-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'dependencia-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'dependencia-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'dependencia-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-dependencia-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'dependencia-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-dependencia-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-dependencia-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-dependencia-create', {view: this});
                this.currentDependenciaModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-dependencia-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-dependencia-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-dependencia-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-dependencia-list', {view: this, data: data});
                var self = this;
				if(!this.dependenciaModelList){
                 this.dependenciaModelList = new this.listModelClass();
				}
                this.dependenciaModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-dependencia-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'dependencia-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-dependencia-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-dependencia-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-dependencia-edit', {view: this, id: id, data: data});
                if (this.dependenciaModelList) {
                    this.currentDependenciaModel = this.dependenciaModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-dependencia-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentDependenciaModel = new this.modelClass({id: id});
                    this.currentDependenciaModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-dependencia-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'dependencia-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-dependencia-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-dependencia-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-dependencia-delete', {view: this, id: id});
                var deleteModel;
                if (this.dependenciaModelList) {
                    deleteModel = this.dependenciaModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-dependencia-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'dependencia-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-dependenciaForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-dependencia-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-dependencia-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-dependencia-save', {view: this, model : model});
                this.currentDependenciaModel.set(model);
                this.currentDependenciaModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-dependencia-save', {model: self.currentDependenciaModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'dependencia-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({dependencias: self.dependenciaModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({dependencia: self.currentDependenciaModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._DependenciaController;
});