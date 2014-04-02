define([], function() {
    App.Model._ProductoModel = Backbone.Model.extend({
        defaults: {
 
		 'tipo' : ''
 ,  
		 'minimaCantidad' : ''
 ,  
		 'name' : ''
 ,  
		 'maximaCantidad' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._ProductoList = Backbone.Collection.extend({
        model: App.Model._ProductoModel,
        initialize: function() {
        }

    });
    return App.Model._ProductoModel;
});