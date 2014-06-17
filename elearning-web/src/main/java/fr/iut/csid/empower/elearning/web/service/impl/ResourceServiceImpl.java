package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.CourseSessionDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.ResourceDAO;
import fr.iut.csid.empower.elearning.web.dto.impl.ResourceDTO;
import fr.iut.csid.empower.elearning.web.service.ResourceService;
import fr.iut.csid.empower.elearning.web.service.ResourceStorageService;

@Named
public class ResourceServiceImpl extends AbstractCrudService<Resource, Long> implements ResourceService {

	@Inject
	private ResourceDAO resourceDAO;
	@Inject
	private CourseSessionDAO courseSessionDAO;
	// service de stockage physique
	@Inject
	private ResourceStorageService resourceStorageService;

	@Override
	protected JpaRepository<Resource, Long> getDAO() {
		return resourceDAO;
	}

	@Override
	public Resource save(Resource entityToSave) {

		return super.save(entityToSave);
	}

	@Override
	public Resource immediateSave(Resource entityToSave) {
		return super.immediateSave(entityToSave);
	}

	@Override
	public void delete(Resource entityToDelete) {

		super.delete(entityToDelete);
	}

	@Override
	public Resource find(Long id) {

		return super.find(id);
	}

	@Override
	public List<Resource> findAll() {

		return super.findAll();
	}

	@Override
	public List<Resource> findByOwner(Long ownerEntityId) {
		return resourceDAO.findByOwnerSession(courseSessionDAO.findOne(ownerEntityId));
	}

	@Override
	public Resource createFromDTO(ResourceDTO entityDTO) {

		return null;
	}

	@Override
	public Resource saveFromDTO(ResourceDTO entityDTO, Long id) {

		return null;
	}

}
