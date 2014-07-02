package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.CourseSessionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.ResourceRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.ResourceDTO;
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

	private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

	@Override
	protected JpaRepository<SessionResource, Long> getRepository() {
		return resourceRepository;
	}

	@Override
	public SessionResource save(SessionResource entityToSave) {

		return super.save(entityToSave);
	}

	@Override
	public SessionResource immediateSave(SessionResource entityToSave) {
		return super.immediateSave(entityToSave);
	}

	@Override
	public void delete(SessionResource entityToDelete) {

		super.delete(entityToDelete);
	}

	@Override
	public SessionResource find(Long id) {

		return super.find(id);
	}

	@Override
	public List<SessionResource> findAll() {

		return super.findAll();
	}

	@Override
	public List<SessionResource> findByOwner(Long ownerEntityId) {
		return resourceRepository.findByOwnerSession(courseSessionRepository.findOne(ownerEntityId));
	}

	@Override
	public SessionResource createFromDTO(ResourceDTO entityDTO) {
		logger.info("Call on createFromDTO");
		return null;
	}

	@Override
	public SessionResource saveFromDTO(ResourceDTO entityDTO, Long id) {

		return null;
	}

}
