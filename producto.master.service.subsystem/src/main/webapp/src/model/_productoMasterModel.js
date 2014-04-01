define([], function() {
    App.Model._ProductoMasterModel = Backbone.Model.extend({
     
    });

    App.Model._ProductoMasterList = Backbone.Collection.extend({
        model: App.Model._ProductoMasterModel,
        initialize: function() {
        }

    });
    return App.Model._ProductoMasterModel;
    
});