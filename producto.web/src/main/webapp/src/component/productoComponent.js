define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/productoModel', 'controller/productoController'], function() {
    App.Component.ProductoComponent = App.Component._CRUDComponent.extend({
        name: 'producto',
        model: App.Model.ProductoModel,
        listModel: App.Model.ProductoList,
        controller : App.Controller.ProductoController
    });
    return App.Component.ProductoComponent;
});