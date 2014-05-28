package fr.iut.csid.empower.elearning.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.exception.UserLoginAlreadyExistsException;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentDAO;

/**
 * Service d'enregistrement d'un étudiant.
 */
@Named
public class StudentRegistrationService {

	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(StudentRegistrationService.class);

	@Inject
	private StudentDAO studentDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public Student registerStudent(Student student) {
		if (userLoginExists(student)) {
			throw new UserLoginAlreadyExistsException();
		}
		logger.info("Student registration ok");
		return studentDAO.save(student);
	}

	@Transactional(readOnly = true)
	public boolean userLoginExists(Student student) {
		Student exist = studentDAO.findByLogin(student.getLogin());
		if (exist == null) {
			return false;
		}
		return true;
	}

}
