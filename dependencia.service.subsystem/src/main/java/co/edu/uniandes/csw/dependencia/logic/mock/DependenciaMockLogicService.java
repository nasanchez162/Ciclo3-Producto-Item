
package co.edu.uniandes.csw.dependencia.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.dependencia.logic.api.IDependenciaLogicService;

@Alternative
@Singleton
public class DependenciaMockLogicService extends _DependenciaMockLogicService implements IDependenciaLogicService {
	
}