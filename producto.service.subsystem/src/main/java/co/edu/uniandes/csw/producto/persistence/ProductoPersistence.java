
package co.edu.uniandes.csw.producto.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ProductoPersistence extends _ProductoPersistence  implements IProductoPersistence {

}