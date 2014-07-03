package fr.iut.csid.empower.elearning.web.service;

import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.CrudService;

public interface CourseSubscriptionService extends CrudService<CourseSubscription, Long> {

	// TODO uniformiser la nature des paramètres
	/**
	 * Crée une souscription d'un étudiant à un cours donné
	 * 
	 * @param student
	 *            : étudiant
	 * @param courseId
	 *            : id du cours cible de l'inscription
	 * @return
	 */
	public CourseSubscription subscribe(Student student, Long courseId);

	/**
	 * Supprime une souscription d'un étudiant à un cours donné
	 * 
	 * @param student
	 *            : étudiant
	 * @param courseId
	 *            : id du cours cible de l'inscription
	 * @return
	 */
	public String unsubscribe(Student student, Long courseId);

	/**
	 * Supprime une souscription d'un étudiant à un cours donné
	 * 
	 * @param student
	 *            : étudiant
	 * @param courseId
	 *            : id du cours cible de la suppression
	 * @return
	 */

}
