package co.edu.uniandes.csw.dependencia.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.dependencia.logic.api.IDependenciaLogicService;
import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;


public abstract class _DependenciaService {

	@Inject
	protected IDependenciaLogicService dependenciaLogicService;
	
	@POST
	public DependenciaDTO createDependencia(DependenciaDTO dependencia){
		return dependenciaLogicService.createDependencia(dependencia);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteDependencia(@PathParam("id") Long id){
		dependenciaLogicService.deleteDependencia(id);
	}
	
	@GET
	public List<DependenciaDTO> getDependencias(){
		return dependenciaLogicService.getDependencias();
	}
	
	@GET
	@Path("{id}")
	public DependenciaDTO getDependencia(@PathParam("id") Long id){
		return dependenciaLogicService.getDependencia(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateDependencia(@PathParam("id") Long id, DependenciaDTO dependencia){
		dependenciaLogicService.updateDependencia(dependencia);
	}
	
}