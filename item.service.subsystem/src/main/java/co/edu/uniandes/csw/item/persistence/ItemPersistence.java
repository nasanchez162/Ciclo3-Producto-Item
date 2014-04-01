
package co.edu.uniandes.csw.item.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ItemPersistence extends _ItemPersistence  implements IItemPersistence {

}