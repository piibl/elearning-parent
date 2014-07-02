package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.gridfs.GridFSDBFile;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.CourseSessionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.ResourceRepository;
import fr.iut.csid.empower.elearning.web.service.ResourceService;
import fr.iut.csid.empower.elearning.web.service.ResourceStorageService;

@Named
public class ResourceServiceImpl extends AbstractCrudService<SessionResource, Long> implements ResourceService {

	@Inject
	private ResourceRepository resourceRepository;
	@Inject
	private CourseSessionRepository courseSessionRepository;
	// service de stockage physique
	@Inject
	private ResourceStorageService resourceStorageService;

	// private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

	@Override
	protected JpaRepository<SessionResource, Long> getRepository() {
		return resourceRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SessionResource save(SessionResource entityToSave) {
		// Stockage du fichier en base mongodb
		resourceStorageService.save(entityToSave.getContentStream(), entityToSave.getFileType(), entityToSave.getStoredFilename());
		return super.save(entityToSave);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(SessionResource entityToDelete) {
		// Destruction du fichier en base mongodb
		resourceStorageService.deleteByFilename(entityToDelete.getStoredFilename());
		super.delete(entityToDelete);
	}

	@Override
	public List<SessionResource> findByOwner(Long ownerEntityId) {
		return resourceRepository.findByOwnerSession(courseSessionRepository.findOne(ownerEntityId));
	}

	@Override
	@Transactional(readOnly = true)
	public GridFSDBFile getPhysicalResource(Long resourceId) {
		SessionResource resource = resourceRepository.findOne(resourceId);
		// Stockage du fichier en base mongodb
		return resourceStorageService.getByResourceName(resource.getStoredFilename());
	}
}
