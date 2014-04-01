
package co.edu.uniandes.csw.dependencia.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.dependencia.persistence.entity.DependenciaEntity;

public abstract class _DependenciaConverter {


	public static DependenciaDTO entity2PersistenceDTO(DependenciaEntity entity){
		if (entity != null) {
			DependenciaDTO dto = new DependenciaDTO();
				dto.setId(entity.getId());
				dto.setCantidadDepen(entity.getCantidadDepen());
				dto.setName(entity.getName());
			return dto;
		}else{
			return null;
		}
	}
	
	public static DependenciaEntity persistenceDTO2Entity(DependenciaDTO dto){
		if(dto!=null){
			DependenciaEntity entity=new DependenciaEntity();
			entity.setId(dto.getId());
			entity.setCantidadDepen(dto.getCantidadDepen());
			entity.setName(dto.getName());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<DependenciaDTO> entity2PersistenceDTOList(List<DependenciaEntity> entities){
		List<DependenciaDTO> dtos=new ArrayList<DependenciaDTO>();
		for(DependenciaEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<DependenciaEntity> persistenceDTO2EntityList(List<DependenciaDTO> dtos){
		List<DependenciaEntity> entities=new ArrayList<DependenciaEntity>();
		for(DependenciaDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}