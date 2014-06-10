package fr.iut.csid.empower.elearning.core.service;



import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.dto.impl.UserDTO;

public interface StudentService extends CrudService<Student, Long, UserDTO> {

	/**
	 * Retourne l'utilisateur par login
	 * @param login
	 * @return
	 */
	public Student findByLogin(String login);
	
	public List<Course> findUnsubscribedCourses(Long id);

}
