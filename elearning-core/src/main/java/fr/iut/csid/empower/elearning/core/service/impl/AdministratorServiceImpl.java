package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.service.AdministratorService;
import fr.iut.csid.empower.elearning.core.service.dao.user.AdministratorDAO;

/**
 * 
 */
@Named
public class AdministratorServiceImpl extends AbstractCrudService<Administrator, Long> implements AdministratorService {

	@Inject
	private AdministratorDAO administratorDAO;

	@Override
	protected JpaRepository<Administrator, Long> getDAO() {
		return administratorDAO;
	}

}
