package fr.iut.csid.empower.elearning.core.service.dao.course;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.service.dao.GenericDAO;

/**
 * DAO COURS
 * 
 * @author Pierre_pers
 * 
 */
public interface CourseDAO extends GenericDAO<Course> {

	/**
	 * Retourne tous les cours ayant un nombre d'inscrits plus faible que
	 * l'entier passé en paramètre
	 * 
	 * @param maxSubscriptions
	 *            nombre de subscriptions maximum, exclusif
	 * @return
	 */
	public List<Course> findBySubscriptionNumber(Long maxSubscriptions);

	/**
	 * Retourne le cours correspondant à l'intitulé passé en paramètre
	 * 
	 * @param courseLabel
	 *            intitulé du cours recherché
	 * @return
	 */
	public Course findByLabel(String courseLabel);

}
