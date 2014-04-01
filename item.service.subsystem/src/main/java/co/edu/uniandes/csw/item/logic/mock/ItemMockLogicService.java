
package co.edu.uniandes.csw.item.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.item.logic.api.IItemLogicService;

@Alternative
@Singleton
public class ItemMockLogicService extends _ItemMockLogicService implements IItemLogicService {
	
}