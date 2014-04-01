
package co.edu.uniandes.csw.item.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.logic.api._IItemLogicService;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;

public abstract class _ItemLogicService implements _IItemLogicService {

	@Inject
	protected IItemPersistence persistance;

	public ItemDTO createItem(ItemDTO item){
		return persistance.createItem( item); 
    }

	public List<ItemDTO> getItems(){
		return persistance.getItems(); 
	}

	public ItemDTO getItem(Long id){
		return persistance.getItem(id); 
	}

	public void deleteItem(Long id){
	    persistance.deleteItem(id); 
	}

	public void updateItem(ItemDTO item){
	    persistance.updateItem(item); 
	}	
}