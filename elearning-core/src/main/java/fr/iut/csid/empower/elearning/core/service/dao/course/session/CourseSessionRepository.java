package fr.iut.csid.empower.elearning.core.service.dao.course.session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {

	/**
	 * Retourne une liste ordonnée des sessions enregistrées pour un cours
	 * 
	 * @param course
	 * @return
	 */
	public List<CourseSession> findByOwnerCourseOrderBySessionRankAsc(Course course);

	/**
	 * Retourne le nombre de sessions enregistrées pour un cours donné
	 * 
	 * @param course
	 * @return
	 */
	public int countByOwnerCourse(Course course);

	/**
	 * Retourne la session au rang donné pour le cours donné
	 * 
	 * @param course
	 * @param sessionRank
	 * @return
	 */
	public CourseSession findByOwnerCourseAndSessionRank(Course course, Long sessionRank);

}
