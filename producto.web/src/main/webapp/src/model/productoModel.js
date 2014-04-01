define(['model/_productoModel'], function() {
    App.Model.ProductoModel = App.Model._ProductoModel.extend({

    });

    App.Model.ProductoList = App.Model._ProductoList.extend({
        model: App.Model.ProductoModel
    });

    return  App.Model.ProductoModel;

});