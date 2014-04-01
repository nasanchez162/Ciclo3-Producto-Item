
package co.edu.uniandes.csw.producto.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.logic.api._IProductoLogicService;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;

public abstract class _ProductoLogicService implements _IProductoLogicService {

	@Inject
	protected IProductoPersistence persistance;

	public ProductoDTO createProducto(ProductoDTO producto){
		return persistance.createProducto( producto); 
    }

	public List<ProductoDTO> getProductos(){
		return persistance.getProductos(); 
	}

	public ProductoDTO getProducto(Long id){
		return persistance.getProducto(id); 
	}

	public void deleteProducto(Long id){
	    persistance.deleteProducto(id); 
	}

	public void updateProducto(ProductoDTO producto){
	    persistance.updateProducto(producto); 
	}	
}