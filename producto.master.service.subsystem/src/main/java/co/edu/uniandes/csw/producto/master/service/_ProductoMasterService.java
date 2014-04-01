package co.edu.uniandes.csw.producto.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.producto.master.logic.api.IProductoMasterLogicService;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;

public abstract class _ProductoMasterService {

    @Inject
    protected IProductoMasterLogicService productoLogicService;

    @POST
    public ProductoMasterDTO createProducto(ProductoMasterDTO producto) {
        return productoLogicService.createMasterProducto(producto);
    }

    @DELETE
    @Path("{id}")
    public void deleteProducto(@PathParam("id") Long id) {
        productoLogicService.deleteMasterProducto(id);
    }
    
    @GET
    @Path("{id}")
    public ProductoMasterDTO getProducto(@PathParam("id") Long id) {
        return productoLogicService.getMasterProducto(id);
    }

    @PUT
    @Path("{id}")
    public void updateProducto(@PathParam("id") Long id, ProductoMasterDTO producto) {
        productoLogicService.updateMasterProducto(producto);
    }

}
