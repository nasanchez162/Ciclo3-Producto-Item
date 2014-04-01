define(['model/itemModel'], function(itemModel) {
    App.Controller._ItemController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#item').html());
            this.listTemplate = _.template($('#itemList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'item-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'item-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'item-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'item-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-item-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'item-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-item-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-item-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-item-create', {view: this});
                this.currentItemModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-item-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-item-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-item-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-item-list', {view: this, data: data});
                var self = this;
				if(!this.itemModelList){
                 this.itemModelList = new this.listModelClass();
				}
                this.itemModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-item-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'item-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-item-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-item-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-item-edit', {view: this, id: id, data: data});
                if (this.itemModelList) {
                    this.currentItemModel = this.itemModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-item-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentItemModel = new this.modelClass({id: id});
                    this.currentItemModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-item-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'item-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-item-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-item-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-item-delete', {view: this, id: id});
                var deleteModel;
                if (this.itemModelList) {
                    deleteModel = this.itemModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-item-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'item-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-itemForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-item-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-item-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-item-save', {view: this, model : model});
                this.currentItemModel.set(model);
                this.currentItemModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-item-save', {model: self.currentItemModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'item-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({items: self.itemModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({item: self.currentItemModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ItemController;
});