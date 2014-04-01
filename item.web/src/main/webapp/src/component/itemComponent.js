define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/itemModel', 'controller/itemController'], function() {
    App.Component.ItemComponent = App.Component._CRUDComponent.extend({
        name: 'item',
        model: App.Model.ItemModel,
        listModel: App.Model.ItemList,
        controller : App.Controller.ItemController
    });
    return App.Component.ItemComponent;
});