
package co.edu.uniandes.csw.producto.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.api._IProductoPersistence;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;

public abstract class _ProductoPersistence implements _IProductoPersistence {

	@PersistenceContext(unitName="ProductoPU")
	protected EntityManager entityManager;
	
	public ProductoDTO createProducto(ProductoDTO producto) {
		ProductoEntity entity=ProductoConverter.persistenceDTO2Entity(producto);
		entityManager.persist(entity);
		return ProductoConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ProductoDTO> getProductos() {
		Query q = entityManager.createQuery("select u from ProductoEntity u");
		return ProductoConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ProductoDTO getProducto(Long id) {
		return ProductoConverter.entity2PersistenceDTO(entityManager.find(ProductoEntity.class, id));
	}

	public void deleteProducto(Long id) {
		ProductoEntity entity=entityManager.find(ProductoEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateProducto(ProductoDTO detail) {
		ProductoEntity entity=entityManager.merge(ProductoConverter.persistenceDTO2Entity(detail));
		ProductoConverter.entity2PersistenceDTO(entity);
	}

}