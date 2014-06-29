package fr.iut.csid.empower.elearning.web.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.user.AdministratorRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.service.AdministratorService;

/**
 * 
 */
@Named
public class AdministratorServiceImpl extends AbstractCrudService<Administrator, Long> implements AdministratorService {

	@Inject
	private AdministratorRepository administratorRepository;

	@Override
	protected JpaRepository<Administrator, Long> getRepository() {
		return administratorRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Administrator createFromDTO(UserDTO entityDTO) {
		Administrator admin = new Administrator(entityDTO.getFirstName(), entityDTO.getLastName(), entityDTO.getLogin(), entityDTO.getPassword(),
				entityDTO.getEmail());
		return administratorRepository.save(admin);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Administrator saveFromDTO(UserDTO entityDTO, Long id) {
		Administrator admin = administratorRepository.findOne(id);
		if (admin != null) {
			// Pas de questions, on reporte tous les changements
			admin.setFirstName(entityDTO.getFirstName());
			admin.setLastName(entityDTO.getLastName());
			admin.setLogin(entityDTO.getLogin());
			admin.setEmail(entityDTO.getEmail());
			// TODO ??? sécurité ???
			admin.setPassword(entityDTO.getPassword());
			return administratorRepository.save(admin);
		} else
			throw new UserNotExistsException();

	}

}
