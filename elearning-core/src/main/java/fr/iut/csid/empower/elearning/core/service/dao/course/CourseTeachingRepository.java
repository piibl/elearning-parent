package fr.iut.csid.empower.elearning.core.service.dao.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;

/**
 * Repository COURSETEACHING
 * 
 * @author Pierre_pers
 */
public interface CourseTeachingRepository extends JpaRepository<CourseTeaching, Long> {

	/**
	 * Retourne les affiliations de cours pour un enseignant donné
	 * 
	 * @param teacher
	 * @return
	 */
	public List<CourseTeaching> findByTeacher(Teacher teacher);

	/**
	 * Retourne les enseignants affiliés à un cours donné
	 * 
	 * @param cours
	 * @return
	 */
	public List<CourseTeaching> findByCourse(Course course);

}
