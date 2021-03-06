package fr.iut.csid.empower.elearning.core.service;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.dto.impl.UserDTO;

public interface StudentService extends CrudService<Student, Long, UserDTO> {

	/**
	 * Retourne l'utilisateur par login
	 * 
	 * @param login
	 * @return
	 */
	public Student findByLogin(String login);

	/**
	 * Retourne tous les cours auxquels n'est pas inscrit l'étudiant
	 * 
	 * @param studentId
	 *            : id de l'étudiant
	 * @return
	 */
	public List<Course> findUnsubscribedCourses(Long studentId);

	/**
	 * Retourne tous les cours auxquels est inscrit l'étudiant
	 * 
	 * @param studentId
	 *            : id de l'étudiant
	 * @return
	 */
	public List<Course> findSubscribedCourses(Long studentId);

}
