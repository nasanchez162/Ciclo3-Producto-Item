package co.edu.uniandes.csw.producto.master.logic.ejb;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.dependencia.persistence.api.IDependenciaPersistence;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.api._IProductoMasterLogicService;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.master.persistence.api.IProductoMasterPersistence;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoItemEntity;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDependenciaEntity;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import javax.inject.Inject;

public abstract class _ProductoMasterLogicService implements _IProductoMasterLogicService {

    @Inject
    protected IProductoPersistence productoPersistance;
    @Inject
    protected IProductoMasterPersistence productoMasterPersistance;
    @Inject
    protected IItemPersistence itemPersistance;
    @Inject
    protected IDependenciaPersistence dependenciaPersistance;

    public ProductoMasterDTO createMasterProducto(ProductoMasterDTO producto) {
        ProductoDTO persistedProductoDTO = productoPersistance.createProducto(producto.getProductoEntity());
        if (producto.getCreateItem() != null) {
            for (ItemDTO itemDTO : producto.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                ProductoItemEntity productoItemEntity = new ProductoItemEntity(persistedProductoDTO.getId(), persistedItemDTO.getId());
                productoMasterPersistance.createProductoItem(productoItemEntity);
            }
        }
        if (producto.getCreateDependencia() != null) {
            for (DependenciaDTO dependenciaDTO : producto.getCreateDependencia()) {
                DependenciaDTO persistedDependenciaDTO = dependenciaPersistance.createDependencia(dependenciaDTO);
                ProductoDependenciaEntity productoDependenciaEntity = new ProductoDependenciaEntity(persistedProductoDTO.getId(), persistedDependenciaDTO.getId());
                productoMasterPersistance.createProductoDependencia(productoDependenciaEntity);
            }
        }
        return producto;
    }

    public ProductoMasterDTO getMasterProducto(Long id) {
        return productoMasterPersistance.getProducto(id);
    }

    public void deleteMasterProducto(Long id) {
        productoPersistance.deleteProducto(id);
    }

    public void updateMasterProducto(ProductoMasterDTO producto) {
        productoPersistance.updateProducto(producto.getProductoEntity());

        //---- FOR RELATIONSHIP
        // persist new item
        if (producto.getCreateItem() != null) {
            for (ItemDTO itemDTO : producto.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                ProductoItemEntity productoItemEntity = new ProductoItemEntity(producto.getProductoEntity().getId(), persistedItemDTO.getId());
                productoMasterPersistance.createProductoItem(productoItemEntity);
            }
        }
        // update item
        if (producto.getUpdateItem() != null) {
            for (ItemDTO itemDTO : producto.getUpdateItem()) {
                itemPersistance.updateItem(itemDTO);
            }
        }
        // delete item
        if (producto.getDeleteItem() != null) {
            for (ItemDTO itemDTO : producto.getDeleteItem()) {
                productoMasterPersistance.deleteProductoItem(producto.getProductoEntity().getId(), itemDTO.getId());
                itemPersistance.deleteItem(itemDTO.getId());
            }
        }
        // persist new dependencia
        if (producto.getCreateDependencia() != null) {
            for (DependenciaDTO dependenciaDTO : producto.getCreateDependencia()) {
                DependenciaDTO persistedDependenciaDTO = dependenciaPersistance.createDependencia(dependenciaDTO);
                ProductoDependenciaEntity productoDependenciaEntity = new ProductoDependenciaEntity(producto.getProductoEntity().getId(), persistedDependenciaDTO.getId());
                productoMasterPersistance.createProductoDependencia(productoDependenciaEntity);
            }
        }
        // update dependencia
        if (producto.getUpdateDependencia() != null) {
            for (DependenciaDTO dependenciaDTO : producto.getUpdateDependencia()) {
                dependenciaPersistance.updateDependencia(dependenciaDTO);
            }
        }
        // delete dependencia
        if (producto.getDeleteDependencia() != null) {
            for (DependenciaDTO dependenciaDTO : producto.getDeleteDependencia()) {
                productoMasterPersistance.deleteProductoDependencia(producto.getProductoEntity().getId(), dependenciaDTO.getId());
                dependenciaPersistance.deleteDependencia(dependenciaDTO.getId());
            }
        }
    }
}
