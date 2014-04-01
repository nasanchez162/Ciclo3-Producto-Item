package co.edu.uniandes.csw.producto.master.persistence.converter;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDependenciaEntity;
import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.dependencia.persistence.converter.DependenciaConverter;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _ProductoMasterConverter {

    public static ProductoMasterDTO entity2PersistenceDTO(ProductoEntity productoEntity 
    ,List<ProductoItemEntity> productoItemEntity 
    ,List<ProductoDependenciaEntity> productoDependenciaEntity 
    ) {
        ProductoDTO productoDTO = ProductoConverter.entity2PersistenceDTO(productoEntity);
        ArrayList<ItemDTO> itemEntities = new ArrayList<ItemDTO>(productoItemEntity.size());
        for (ProductoItemEntity productoItem : productoItemEntity) {
            itemEntities.add(ItemConverter.entity2PersistenceDTO(productoItem.getItemEntity()));
        }
        ArrayList<DependenciaDTO> dependenciaEntities = new ArrayList<DependenciaDTO>(productoDependenciaEntity.size());
        for (ProductoDependenciaEntity productoDependencia : productoDependenciaEntity) {
            dependenciaEntities.add(DependenciaConverter.entity2PersistenceDTO(productoDependencia.getDependenciaEntity()));
        }
        ProductoMasterDTO respDTO  = new ProductoMasterDTO();
        respDTO.setProductoEntity(productoDTO);
        respDTO.setListItem(itemEntities);
        respDTO.setListDependencia(dependenciaEntities);
        return respDTO;
    }

}