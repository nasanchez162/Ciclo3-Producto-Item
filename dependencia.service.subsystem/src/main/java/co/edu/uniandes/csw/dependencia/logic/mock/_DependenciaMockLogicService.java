
package co.edu.uniandes.csw.dependencia.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.dependencia.logic.api._IDependenciaLogicService;

public abstract class _DependenciaMockLogicService implements _IDependenciaLogicService {

	private Long id= new Long(1);
	protected List<DependenciaDTO> data=new ArrayList<DependenciaDTO>();

	public DependenciaDTO createDependencia(DependenciaDTO dependencia){
		id++;
		dependencia.setId(id);
		return dependencia;
    }

	public List<DependenciaDTO> getDependencias(){
		return data; 
	}

	public DependenciaDTO getDependencia(Long id){
		for(DependenciaDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteDependencia(Long id){
	    DependenciaDTO delete=null;
		for(DependenciaDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateDependencia(DependenciaDTO dependencia){
	    DependenciaDTO delete=null;
		for(DependenciaDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(dependencia);
		} 
	}	
}