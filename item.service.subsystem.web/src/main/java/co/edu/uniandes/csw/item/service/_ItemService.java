package co.edu.uniandes.csw.item.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.item.logic.api.IItemLogicService;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;


public abstract class _ItemService {

	@Inject
	protected IItemLogicService itemLogicService;
	
	@POST
	public ItemDTO createItem(ItemDTO item){
		return itemLogicService.createItem(item);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteItem(@PathParam("id") Long id){
		itemLogicService.deleteItem(id);
	}
	
	@GET
	public List<ItemDTO> getItems(){
		return itemLogicService.getItems();
	}
	
	@GET
	@Path("{id}")
	public ItemDTO getItem(@PathParam("id") Long id){
		return itemLogicService.getItem(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateItem(@PathParam("id") Long id, ItemDTO item){
		itemLogicService.updateItem(item);
	}
	
}