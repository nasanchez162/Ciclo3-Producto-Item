package co.edu.uniandes.csw.producto.master.persistence.entity;

import co.edu.uniandes.csw.dependencia.persistence.entity.DependenciaEntity;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(ProductoDependenciaEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ProductoDependenciaEntity.getDependenciaListForProducto", query = "SELECT  u FROM ProductoDependenciaEntity u WHERE u.productoId=:productoId"),
    @NamedQuery(name = "ProductoDependenciaEntity.deleteProductoDependencia", query = "DELETE FROM ProductoDependenciaEntity u WHERE u.dependenciaId=:dependenciaId and  u.productoId=:productoId")
})
public class ProductoDependenciaEntity implements Serializable {

    @Id
    @Column(name = "productoId")
    private Long productoId;
    @Id
    @Column(name = "dependenciaId")
    private Long dependenciaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dependenciaId", referencedColumnName = "id")
    @JoinFetch
    private DependenciaEntity dependenciaEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;

    public ProductoDependenciaEntity() {
    }

    public ProductoDependenciaEntity(Long productoId, Long dependenciaId) {
        this.productoId = productoId;
        this.dependenciaId = dependenciaId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getDependenciaId() {
        return dependenciaId;
    }

    public void setDependenciaId(Long dependenciaId) {
        this.dependenciaId = dependenciaId;
    }

    public DependenciaEntity getDependenciaEntity() {
        return dependenciaEntity;
    }

    public void setDependenciaEntity(DependenciaEntity dependenciaEntity) {
        this.dependenciaEntity = dependenciaEntity;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

}
