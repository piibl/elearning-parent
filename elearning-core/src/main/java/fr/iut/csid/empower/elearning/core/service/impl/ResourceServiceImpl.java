package fr.iut.csid.empower.elearning.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource;
import fr.iut.csid.empower.elearning.core.dto.impl.ResourceDTO;
import fr.iut.csid.empower.elearning.core.service.ResourceService;
import fr.iut.csid.empower.elearning.core.service.ResourceStorageService;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.ResourceDAO;

@Named
public class ResourceServiceImpl extends AbstractCrudService<Resource, Long> implements ResourceService {

	@Inject
	private ResourceDAO resourceDAO;
	// service de stockage physique
	@Inject
	private ResourceStorageService resourceStorageService;

	@Override
	protected JpaRepository<Resource, Long> getDAO() {
		// TODO Auto-generated method stub
		return resourceDAO;
	}

	@Override
	public Resource save(Resource entityToSave) {

		return super.save(entityToSave);
	}

	@Override
	public Resource immediateSave(Resource entityToSave) {
		// TODO Auto-generated method stub
		return super.immediateSave(entityToSave);
	}

	@Override
	public void delete(Resource entityToDelete) {
		// TODO Auto-generated method stub
		super.delete(entityToDelete);
	}

	@Override
	public Resource find(Long id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

	@Override
	public List<Resource> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public List<Resource> findByOwner(Long ownerEntityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource createFromDTO(ResourceDTO entityDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource saveFromDTO(ResourceDTO entityDTO, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
