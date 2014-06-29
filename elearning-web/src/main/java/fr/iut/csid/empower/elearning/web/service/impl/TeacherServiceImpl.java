package fr.iut.csid.empower.elearning.web.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.service.TeacherService;

/**
 * 
 */
@Named
public class TeacherServiceImpl extends AbstractCrudService<Teacher, Long> implements TeacherService {

	private Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

	@Inject
	private TeacherRepository teacherRepository;

	@Override
	protected JpaRepository<Teacher, Long> getRepository() {
		return teacherRepository;
	}

	@Override
	public Teacher findByLogin(String login) {
		return teacherRepository.findByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Teacher createFromDTO(UserDTO entityDTO) {
		logger.info("Try saving entityDTO [" + entityDTO.toString() + "] : [" + entityDTO.getFirstName() + "][" + entityDTO.getLastName() + "]["
				+ entityDTO.getLogin() + "][" + entityDTO.getPassword() + "][" + entityDTO.getEmail() + "]");
		Teacher teacher = new Teacher(entityDTO.getFirstName(), entityDTO.getLastName(), entityDTO.getLogin(), entityDTO.getPassword(),
				entityDTO.getEmail());
		return teacherRepository.save(teacher);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Teacher saveFromDTO(UserDTO entityDTO, Long id) {
		Teacher teacher = teacherRepository.findOne(id);
		if (teacher != null) {
			// Pas de questions, on reporte tous les changements
			teacher.setFirstName(entityDTO.getFirstName());
			teacher.setLastName(entityDTO.getLastName());
			teacher.setLogin(entityDTO.getLogin());
			teacher.setEmail(entityDTO.getEmail());
			// TODO ??? sécurité ???
			teacher.setPassword(entityDTO.getPassword());
			return teacherRepository.save(teacher);
		} else
			throw new UserNotExistsException();

	}

}
