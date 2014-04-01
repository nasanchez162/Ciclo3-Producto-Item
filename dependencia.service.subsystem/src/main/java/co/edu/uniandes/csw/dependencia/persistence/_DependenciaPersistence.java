
package co.edu.uniandes.csw.dependencia.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.dependencia.persistence.api._IDependenciaPersistence;
import co.edu.uniandes.csw.dependencia.persistence.converter.DependenciaConverter;
import co.edu.uniandes.csw.dependencia.persistence.entity.DependenciaEntity;

public abstract class _DependenciaPersistence implements _IDependenciaPersistence {

	@PersistenceContext(unitName="DependenciaPU")
	protected EntityManager entityManager;
	
	public DependenciaDTO createDependencia(DependenciaDTO dependencia) {
		DependenciaEntity entity=DependenciaConverter.persistenceDTO2Entity(dependencia);
		entityManager.persist(entity);
		return DependenciaConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<DependenciaDTO> getDependencias() {
		Query q = entityManager.createQuery("select u from DependenciaEntity u");
		return DependenciaConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public DependenciaDTO getDependencia(Long id) {
		return DependenciaConverter.entity2PersistenceDTO(entityManager.find(DependenciaEntity.class, id));
	}

	public void deleteDependencia(Long id) {
		DependenciaEntity entity=entityManager.find(DependenciaEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateDependencia(DependenciaDTO detail) {
		DependenciaEntity entity=entityManager.merge(DependenciaConverter.persistenceDTO2Entity(detail));
		DependenciaConverter.entity2PersistenceDTO(entity);
	}

}