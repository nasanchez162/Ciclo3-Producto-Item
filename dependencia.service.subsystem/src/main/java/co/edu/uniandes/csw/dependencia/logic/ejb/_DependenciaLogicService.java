
package co.edu.uniandes.csw.dependencia.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.dependencia.logic.api._IDependenciaLogicService;
import co.edu.uniandes.csw.dependencia.persistence.api.IDependenciaPersistence;

public abstract class _DependenciaLogicService implements _IDependenciaLogicService {

	@Inject
	protected IDependenciaPersistence persistance;

	public DependenciaDTO createDependencia(DependenciaDTO dependencia){
		return persistance.createDependencia( dependencia); 
    }

	public List<DependenciaDTO> getDependencias(){
		return persistance.getDependencias(); 
	}

	public DependenciaDTO getDependencia(Long id){
		return persistance.getDependencia(id); 
	}

	public void deleteDependencia(Long id){
	    persistance.deleteDependencia(id); 
	}

	public void updateDependencia(DependenciaDTO dependencia){
	    persistance.updateDependencia(dependencia); 
	}	
}