
package co.edu.uniandes.csw.producto.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;

public interface _IProductoPersistence {

	public ProductoDTO createProducto(ProductoDTO detail);
	public List<ProductoDTO> getProductos();
	public ProductoDTO getProducto(Long id);
	public void deleteProducto(Long id);
	public void updateProducto(ProductoDTO detail);
	
}