/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


define([], function() {
    App.Delegate.ProductoMasterDelegate = {
         getAmmountProduct: function(id, callback, callbackError) {
            console.log('getAmmountProduct: ' + id);
            $.ajax({
                url: '/producto.master.service.subsystem/webresources/ProductoMaster/'+id+'/getAmmountProduct',
                type: 'GET',
                data: {},
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        },
        deleteItemProductsByNumber: function(id, num , callback, callbackError) {
            console.log('getAmmountProduct: ' + id + ' , '+num);
            $.ajax({
                url: '/producto.master.service.subsystem/webresources/ProductoMaster/'+id+'/'+num+'/deleteItemProductsByNumber',
                type: 'GET',
                data: {},
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        }
        
    };
});


