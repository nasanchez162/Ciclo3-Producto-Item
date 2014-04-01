define([], function() {
    App.Model._DependenciaModel = Backbone.Model.extend({
        defaults: {
 
		 'cantidadDepen' : ''
 ,  
		 'name' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._DependenciaList = Backbone.Collection.extend({
        model: App.Model._DependenciaModel,
        initialize: function() {
        }

    });
    return App.Model._DependenciaModel;
});