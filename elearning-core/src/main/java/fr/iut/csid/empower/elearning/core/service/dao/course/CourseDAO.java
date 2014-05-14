package fr.iut.csid.empower.elearning.core.service.dao.course;

import java.util.List;

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

	/**
	 * Retourne tous les cours ayant un nombre d'inscrits plus faible que
	 * l'entier passé en paramètre
	 * 
	 * @param maxSubscriptions
	 *            nombre de subscriptions maximum, exclusif
	 * @return
	 */
	@Query("SELECT c FROM Course c WHERE (SELECT COUNT(s) FROM c.students s) < ?1")
	public List<Course> findByStudentsLessThan(Long maxSubscriptions);

	/**
	 * Retourne le cours correspondant à l'intitulé passé en paramètre
	 * 
	 * @param courseLabel
	 *            intitulé du cours recherché
	 * @return
	 */
	public Course findByLabel(String courseLabel);

}
