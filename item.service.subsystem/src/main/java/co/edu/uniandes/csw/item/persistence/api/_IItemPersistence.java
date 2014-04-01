
package co.edu.uniandes.csw.item.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;

public interface _IItemPersistence {

	public ItemDTO createItem(ItemDTO detail);
	public List<ItemDTO> getItems();
	public ItemDTO getItem(Long id);
	public void deleteItem(Long id);
	public void updateItem(ItemDTO detail);
	
}