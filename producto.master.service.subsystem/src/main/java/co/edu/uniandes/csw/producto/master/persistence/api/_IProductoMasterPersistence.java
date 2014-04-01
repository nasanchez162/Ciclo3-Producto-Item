package co.edu.uniandes.csw.producto.master.persistence.api;

import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDependenciaEntity;
import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import java.util.List;

public interface _IProductoMasterPersistence {

    public ProductoItemEntity createProductoItem(ProductoItemEntity entity);

    public void deleteProductoItem(Long productoId, Long itemId);
    
    public List<ItemDTO> getItemListForProducto(Long productoId);
    public ProductoDependenciaEntity createProductoDependencia(ProductoDependenciaEntity entity);

    public void deleteProductoDependencia(Long productoId, Long dependenciaId);
    
    public List<DependenciaDTO> getDependenciaListForProducto(Long productoId);
    
    public ProductoMasterDTO getProducto(Long productoId);

}
