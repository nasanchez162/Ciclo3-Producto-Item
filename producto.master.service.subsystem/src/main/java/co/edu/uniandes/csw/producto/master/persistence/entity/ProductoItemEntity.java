package co.edu.uniandes.csw.producto.master.persistence.entity;

import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;
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
@IdClass(ProductoItemEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ProductoItemEntity.getItemListForProducto", query = "SELECT  u FROM ProductoItemEntity u WHERE u.productoId=:productoId"),
    @NamedQuery(name = "ProductoItemEntity.deleteProductoItem", query = "DELETE FROM ProductoItemEntity u WHERE u.itemId=:itemId and  u.productoId=:productoId")
})
public class ProductoItemEntity implements Serializable {

    @Id
    @Column(name = "productoId")
    private Long productoId;
    @Id
    @Column(name = "itemId")
    private Long itemId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "itemId", referencedColumnName = "id")
    @JoinFetch
    private ItemEntity itemEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;

    public ProductoItemEntity() {
    }

    public ProductoItemEntity(Long productoId, Long itemId) {
        this.productoId = productoId;
        this.itemId = itemId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

}
