package fr.iut.csid.empower.elearning.core.service;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.dto.CourseDTO;

public interface CourseService extends CrudService<Course, Long, CourseDTO> {

	/**
	 * Crée un cours à partir d'un DTO. Le DTO contient l'intitulé du cours et l'id du créateur
	 * 
	 * @param courseDTO
	 *            : DTO
	 * @return
	 */
	public Course createCourse(CourseDTO courseDTO);

	/**
	 * Retourne les cours rattachés à un enseignant donné
	 * 
	 * @param teacherId
	 *            : id de l'enseignant
	 */
	public List<Course> findByTeacher(Long teacherId);

}
