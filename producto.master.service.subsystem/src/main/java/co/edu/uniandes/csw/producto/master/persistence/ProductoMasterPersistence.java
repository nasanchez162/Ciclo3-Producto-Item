package co.edu.uniandes.csw.producto.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.producto.master.persistence.api.IProductoMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class ProductoMasterPersistence extends _ProductoMasterPersistence  implements IProductoMasterPersistence {

}