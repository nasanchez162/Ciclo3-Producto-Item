package co.edu.uniandes.csw.producto.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.producto.logic.api.IProductoLogicService;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;


public abstract class _ProductoService {

	@Inject
	protected IProductoLogicService productoLogicService;
	
	@POST
	public ProductoDTO createProducto(ProductoDTO producto){
		return productoLogicService.createProducto(producto);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteProducto(@PathParam("id") Long id){
		productoLogicService.deleteProducto(id);
	}
	
	@GET
	public List<ProductoDTO> getProductos(){
		return productoLogicService.getProductos();
	}
	
	@GET
	@Path("{id}")
	public ProductoDTO getProducto(@PathParam("id") Long id){
		return productoLogicService.getProducto(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateProducto(@PathParam("id") Long id, ProductoDTO producto){
		productoLogicService.updateProducto(producto);
	}
	
}