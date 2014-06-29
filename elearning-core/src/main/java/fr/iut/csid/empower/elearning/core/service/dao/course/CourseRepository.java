package fr.iut.csid.empower.elearning.core.service.dao.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.user.Student;

/**
 * Repository COURS
 * 
 * @author Pierre_pers
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query("SELECT c FROM Course c WHERE c NOT IN (SELECT cs.course FROM CourseSubscription cs WHERE cs.student = ?1)")
	public List<Course> findUnsubscribedCourses(Student student);

	/**
	 * Retourne le cours correspondant à l'intitulé passé en paramètre
	 * 
	 * @param courseLabel
	 *            intitulé du cours recherché
	 * @return
	 */
	public Course findByLabel(String courseLabel);

}
