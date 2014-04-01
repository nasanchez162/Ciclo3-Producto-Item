package co.edu.uniandes.csw.producto.master.logic.dto;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _ProductoMasterDTO {

 
    protected ProductoDTO productoEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ProductoDTO getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoDTO productoEntity) {
        this.productoEntity = productoEntity;
    }
    
    public List<ItemDTO> createItem;
    public List<ItemDTO> updateItem;
    public List<ItemDTO> deleteItem;
    public List<ItemDTO> listItem;	
    public List<DependenciaDTO> createDependencia;
    public List<DependenciaDTO> updateDependencia;
    public List<DependenciaDTO> deleteDependencia;
    public List<DependenciaDTO> listDependencia;	
	
	
	
    public List<ItemDTO> getCreateItem(){ return createItem; };
    public void setCreateItem(List<ItemDTO> createItem){ this.createItem=createItem; };
    public List<ItemDTO> getUpdateItem(){ return updateItem; };
    public void setUpdateItem(List<ItemDTO> updateItem){ this.updateItem=updateItem; };
    public List<ItemDTO> getDeleteItem(){ return deleteItem; };
    public void setDeleteItem(List<ItemDTO> deleteItem){ this.deleteItem=deleteItem; };
    public List<ItemDTO> getListItem(){ return listItem; };
    public void setListItem(List<ItemDTO> listItem){ this.listItem=listItem; };	
    public List<DependenciaDTO> getCreateDependencia(){ return createDependencia; };
    public void setCreateDependencia(List<DependenciaDTO> createDependencia){ this.createDependencia=createDependencia; };
    public List<DependenciaDTO> getUpdateDependencia(){ return updateDependencia; };
    public void setUpdateDependencia(List<DependenciaDTO> updateDependencia){ this.updateDependencia=updateDependencia; };
    public List<DependenciaDTO> getDeleteDependencia(){ return deleteDependencia; };
    public void setDeleteDependencia(List<DependenciaDTO> deleteDependencia){ this.deleteDependencia=deleteDependencia; };
    public List<DependenciaDTO> getListDependencia(){ return listDependencia; };
    public void setListDependencia(List<DependenciaDTO> listDependencia){ this.listDependencia=listDependencia; };	
	
	
}

