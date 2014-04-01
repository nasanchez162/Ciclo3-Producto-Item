
package co.edu.uniandes.csw.item.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.logic.api._IItemLogicService;

public abstract class _ItemMockLogicService implements _IItemLogicService {

	private Long id= new Long(1);
	protected List<ItemDTO> data=new ArrayList<ItemDTO>();

	public ItemDTO createItem(ItemDTO item){
		id++;
		item.setId(id);
		return item;
    }

	public List<ItemDTO> getItems(){
		return data; 
	}

	public ItemDTO getItem(Long id){
		for(ItemDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteItem(Long id){
	    ItemDTO delete=null;
		for(ItemDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateItem(ItemDTO item){
	    ItemDTO delete=null;
		for(ItemDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(item);
		} 
	}	
}