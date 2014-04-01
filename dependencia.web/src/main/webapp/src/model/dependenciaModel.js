define(['model/_dependenciaModel'], function() {
    App.Model.DependenciaModel = App.Model._DependenciaModel.extend({

    });

    App.Model.DependenciaList = App.Model._DependenciaList.extend({
        model: App.Model.DependenciaModel
    });

    return  App.Model.DependenciaModel;

});