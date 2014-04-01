
package co.edu.uniandes.csw.producto.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.producto.logic.api.IProductoLogicService;

@Alternative
@Singleton
public class ProductoMockLogicService extends _ProductoMockLogicService implements IProductoLogicService {
	
}