package co.edu.uniandes.csw.producto.master.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ProductoMaster")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoMasterService extends _ProductoMasterService {

    @GET
    @Path("{id}/getAmmountProduct")
    public String getAmmountProduct(@PathParam("id") Long id){
        return productoLogicService.getAmmountProduct(id);
    }
    
    @GET
    @Path("{id}/{num}/deleteItemProductsByNumber")
    public Boolean deleteItemProductsByNumber(@PathParam("id") Long id, @PathParam("num") Integer num){
        return productoLogicService.deleteItemProductsByNumber(id,num);
    }
    
}
