package fr.iut.csid.empower.elearning.web.service;

import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource;
import fr.iut.csid.empower.elearning.core.service.CrudService;

/**
 * Service de gestion d'une ressource, pas de support de DTO ici
 */
public interface ResourceService extends CrudService<SessionResource, Long> {
	/**
	 * Retourne la liste des instances appartenant à l'entité
	 * 
	 * @return
	 */
	public List<SessionResource> findByOwner(Long ownerEntityId);

	/**
	 * Retourne l'implémentation physique de la ressource
	 * 
	 * @param resourceId
	 * @return
	 */
	public GridFSDBFile getPhysicalResource(Long resourceId);
}
