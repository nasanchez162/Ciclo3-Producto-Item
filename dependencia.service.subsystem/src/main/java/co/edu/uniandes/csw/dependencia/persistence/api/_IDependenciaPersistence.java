
package co.edu.uniandes.csw.dependencia.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;

public interface _IDependenciaPersistence {

	public DependenciaDTO createDependencia(DependenciaDTO detail);
	public List<DependenciaDTO> getDependencias();
	public DependenciaDTO getDependencia(Long id);
	public void deleteDependencia(Long id);
	public void updateDependencia(DependenciaDTO detail);
	
}