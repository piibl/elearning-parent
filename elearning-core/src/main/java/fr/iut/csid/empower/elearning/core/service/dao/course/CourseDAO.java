package fr.iut.csid.empower.elearning.core.service.dao.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.iut.csid.empower.elearning.core.domain.course.Course;

/**
 * DAO COURS
 * 
 * @author Pierre_pers
 * 
 */
public interface CourseDAO extends JpaRepository<Course, Long> {

	@Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT cs.course FROM CourseSubscription cs WHERE cs.student = ?1)")
	public Course findUnsubscribedCourses(Long studentId);

	/**
	 * Retourne le cours correspondant à l'intitulé passé en paramètre
	 * 
	 * @param courseLabel
	 *            intitulé du cours recherché
	 * @return
	 */
	public Course findByLabel(String courseLabel);

}
