package fr.iut.csid.empower.elearning.core.service;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class RegistrationService {

	private static Logger logger = LoggerFactory.getLogger(RegistrationService.class);

	// @Inject
	// private StudentDAO studentDAO;
	// @Inject
	// private CourseDAO courseDAO;
	//
	// public void register(Student student, Course courseToRegister) {
	// // Si l'étudiant est inscrit à moins de 3 cours
	// System.out.println(student.getCourses().size());
	// if (student.getCourses().size() < 3) {
	// student.getCourses().add(courseToRegister);
	// studentDAO.save(student);
	// courseToRegister.getStudents().add(student);
	// courseDAO.save(courseToRegister);
	//
	// logger.info("Student [" + student.getFirstName() + " "
	// + student.getLastName() + "] is now registred in ["
	// + courseToRegister.getLabel() + "] course.");
	// // return true;
	// } else {
	// logger.info("Student [" + student.getFirstName() + " "
	// + student.getLastName()
	// + "] is already registred in three courses.");
	// // return false;
	// }
	// }
	//
	// public void register(Student student, Course... coursesToRegister) {
	// // Inscriptions multiples
	// for (Course course : coursesToRegister) {
	// register(student, course);
	// }
	// }
}
