package fr.iut.csid.empower.elearning.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.student.Student;
import fr.iut.csid.empower.elearning.core.service.RegistrationService;
import fr.iut.csid.empower.elearning.core.service.dao.student.StudentDAO;

@Named
public class RegistrationService {

	private static Logger logger = LoggerFactory
			.getLogger(RegistrationService.class);

	@Inject
	private StudentDAO studentDAO;

	public void register(Student student, Course courseToRegister) {
		// Si l'étudiant est inscrit à moins de 3 cours
		if (student.getCourses().size() < 3) {
			student.getCourses().add(courseToRegister);
			logger.info("Student [" + student.getFirstName() + " "
					+ student.getLastName() + "] is now registred in ["
					+ courseToRegister.getLabel() + "] course.");
			// return true;
		} else {
			logger.info("Student [" + student.getFirstName() + " "
					+ student.getLastName()
					+ "] is already registred in three courses.");
			// return false;
		}
	}

	public void register(Student student, Course... coursesToRegister) {
		// Inscriptions multiples
		for (Course course : coursesToRegister) {
			register(student, course);
		}
	}
}
