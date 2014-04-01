 
package co.edu.uniandes.csw.producto.master.logic.api;

import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;

public interface _IProductoMasterLogicService {

	public ProductoMasterDTO createMasterProducto(ProductoMasterDTO detail);
    public void updateMasterProducto(ProductoMasterDTO detail);
	public void deleteMasterProducto(Long id); 
	public ProductoMasterDTO getMasterProducto(Long id);
        
}