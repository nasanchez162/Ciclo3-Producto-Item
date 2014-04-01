package co.edu.uniandes.csw.producto.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ProductoItemEntityId implements Serializable{

    private Long productoId;
    private Long itemId;

    @Override
    public int hashCode() {
        return (int) (productoId + itemId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ProductoItemEntityId) {
            ProductoItemEntityId otherId = (ProductoItemEntityId) object;
            return (otherId.productoId == this.productoId) && (otherId.itemId == this.itemId);
        }
        return false;
    }

}
