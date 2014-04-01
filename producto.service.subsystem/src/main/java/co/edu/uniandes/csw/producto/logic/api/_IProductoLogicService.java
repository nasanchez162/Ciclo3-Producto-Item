
package co.edu.uniandes.csw.producto.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;

public interface _IProductoLogicService {

	public ProductoDTO createProducto(ProductoDTO detail);
	public List<ProductoDTO> getProductos();
	public ProductoDTO getProducto(Long id);
	public void deleteProducto(Long id);
	public void updateProducto(ProductoDTO detail);
	
}