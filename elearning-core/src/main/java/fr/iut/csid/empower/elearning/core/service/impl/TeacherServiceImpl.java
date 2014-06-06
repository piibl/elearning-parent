package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.dto.UserDTO;
import fr.iut.csid.empower.elearning.core.service.TeacherService;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherDAO;

/**
 * 
 */
@Named
public class TeacherServiceImpl extends AbstractCrudService<Teacher, Long> implements TeacherService {

	private Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

	@Inject
	private TeacherDAO teacherDAO;

	@Override
	protected JpaRepository<Teacher, Long> getDAO() {
		return teacherDAO;
	}

	@Override
	public Teacher findByLogin(String login) {
		return teacherDAO.findByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Teacher saveFromDTO(UserDTO entityDTO) {
		logger.info("Try saving entityDTO [" + entityDTO.toString() + "] : [" + entityDTO.getFirstName() + "][" + entityDTO.getLastName() + "]["
				+ entityDTO.getLogin() + "][" + entityDTO.getPassword() + "][" + entityDTO.getEmail() + "]");
		Teacher teacher = new Teacher(entityDTO.getFirstName(), entityDTO.getLastName(), entityDTO.getLogin(), entityDTO.getPassword(),
				entityDTO.getEmail());
		return teacherDAO.save(teacher);
	}

}
