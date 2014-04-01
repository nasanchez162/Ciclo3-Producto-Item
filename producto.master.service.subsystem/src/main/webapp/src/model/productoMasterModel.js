define(['model/_productoMasterModel'], function() { 
    App.Model.ProductoMasterModel = App.Model._ProductoMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('producto-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.ProductoModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.productoEntity,options);
            }
        }
    });

    App.Model.ProductoMasterList = App.Model._ProductoMasterList.extend({
        model: App.Model.ProductoMasterModel
    });

    return  App.Model.ProductoMasterModel;

});