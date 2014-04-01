define(['model/productoModel'], function(productoModel) {
    App.Controller._ProductoController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#producto').html());
            this.listTemplate = _.template($('#productoList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'producto-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'producto-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'producto-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'producto-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-producto-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'producto-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-producto-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-producto-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-producto-create', {view: this});
                this.currentProductoModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-producto-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-producto-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-producto-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-producto-list', {view: this, data: data});
                var self = this;
				if(!this.productoModelList){
                 this.productoModelList = new this.listModelClass();
				}
                this.productoModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-producto-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-producto-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-producto-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-producto-edit', {view: this, id: id, data: data});
                if (this.productoModelList) {
                    this.currentProductoModel = this.productoModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-producto-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentProductoModel = new this.modelClass({id: id});
                    this.currentProductoModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-producto-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-producto-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-producto-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-producto-delete', {view: this, id: id});
                var deleteModel;
                if (this.productoModelList) {
                    deleteModel = this.productoModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-producto-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-productoForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-producto-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-producto-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-producto-save', {view: this, model : model});
                this.currentProductoModel.set(model);
                this.currentProductoModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-producto-save', {model: self.currentProductoModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'producto-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({productos: self.productoModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({producto: self.currentProductoModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ProductoController;
});