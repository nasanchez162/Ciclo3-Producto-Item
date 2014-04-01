define(['model/_itemModel'], function() {
    App.Model.ItemModel = App.Model._ItemModel.extend({

    });

    App.Model.ItemList = App.Model._ItemList.extend({
        model: App.Model.ItemModel
    });

    return  App.Model.ItemModel;

});