
package co.edu.uniandes.csw.dependencia.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.dependencia.logic.api.IDependenciaLogicService;

@Default
@Stateless
@LocalBean
public class DependenciaLogicService extends _DependenciaLogicService implements IDependenciaLogicService {

}