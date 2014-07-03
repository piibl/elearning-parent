package fr.iut.csid.empower.elearning.core.service.dao.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;

/**
 * Repository CourseSubscription
 * 
 * @author Pierre_pers
 */
public interface CourseSubscriptionRepository extends JpaRepository<CourseSubscription, Long> {

	/**
	 * Retourne la liste des souscriptions de l'étudiant
	 * 
	 * @param student
	 *            : étudiant
	 * @return
	 */
	public List<CourseSubscription> findByStudent(Student student);

	/**
	 * Retourne toutes les souscription à un cours donné
	 * 
	 * @param course
	 *            : cours cible
	 * @return
	 */
	public List<CourseSubscription> findByCourse(Course course);

	/**
	 * Retourne l'unique souscirption entre un étudiant et un cours donnés
	 * 
	 * @param student
	 * @param course
	 * @return
	 */
	public CourseSubscription findByStudentAndCourse(Student student, Course course);
}
