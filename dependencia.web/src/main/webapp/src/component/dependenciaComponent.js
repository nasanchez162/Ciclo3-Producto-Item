define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/dependenciaModel', 'controller/dependenciaController'], function() {
    App.Component.DependenciaComponent = App.Component._CRUDComponent.extend({
        name: 'dependencia',
        model: App.Model.DependenciaModel,
        listModel: App.Model.DependenciaList,
        controller : App.Controller.DependenciaController
    });
    return App.Component.DependenciaComponent;
});