
package co.edu.uniandes.csw.item.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.item.logic.api.IItemLogicService;

@Default
@Stateless
@LocalBean
public class ItemLogicService extends _ItemLogicService implements IItemLogicService {

}