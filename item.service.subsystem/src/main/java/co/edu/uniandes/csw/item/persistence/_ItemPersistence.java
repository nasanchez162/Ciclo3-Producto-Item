
package co.edu.uniandes.csw.item.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.api._IItemPersistence;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;

public abstract class _ItemPersistence implements _IItemPersistence {

	@PersistenceContext(unitName="ItemPU")
	protected EntityManager entityManager;
	
	public ItemDTO createItem(ItemDTO item) {
		ItemEntity entity=ItemConverter.persistenceDTO2Entity(item);
		entityManager.persist(entity);
		return ItemConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ItemDTO> getItems() {
		Query q = entityManager.createQuery("select u from ItemEntity u");
		return ItemConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ItemDTO getItem(Long id) {
		return ItemConverter.entity2PersistenceDTO(entityManager.find(ItemEntity.class, id));
	}

	public void deleteItem(Long id) {
		ItemEntity entity=entityManager.find(ItemEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateItem(ItemDTO detail) {
		ItemEntity entity=entityManager.merge(ItemConverter.persistenceDTO2Entity(detail));
		ItemConverter.entity2PersistenceDTO(entity);
	}

}