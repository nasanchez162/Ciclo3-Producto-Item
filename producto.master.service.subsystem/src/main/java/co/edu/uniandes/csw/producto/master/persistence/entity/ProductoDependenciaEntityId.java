package co.edu.uniandes.csw.producto.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ProductoDependenciaEntityId implements Serializable{

    private Long productoId;
    private Long dependenciaId;

    @Override
    public int hashCode() {
        return (int) (productoId + dependenciaId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ProductoDependenciaEntityId) {
            ProductoDependenciaEntityId otherId = (ProductoDependenciaEntityId) object;
            return (otherId.productoId == this.productoId) && (otherId.dependenciaId == this.dependenciaId);
        }
        return false;
    }

}
