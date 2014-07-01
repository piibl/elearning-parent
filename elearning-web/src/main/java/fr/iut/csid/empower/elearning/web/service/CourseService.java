package fr.iut.csid.empower.elearning.web.service;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.web.dto.impl.CourseDTO;

public interface CourseService extends DTOSupport<Course, Long, CourseDTO> {

	/**
	 * Retourne les cours rattachés à un enseignant donné
	 * 
	 * @param teacherId
	 *            : id de l'enseignant
	 */
	public List<Course> findByTeacher(Long teacherId);

	/**
	 * Retourne les versions enrichies des cours d'un enseignant <br/>
	 * Sont disponibles : chapitres, souscriptions et affiliations.
	 * 
	 * @param teacherId
	 * @return
	 */
	public List<Course> findEnrichedCoursesByTeacher(Long teacherId);

}
