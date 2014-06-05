package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;

/**
 * 
 */
@Named
public class CourseServiceImpl extends AbstractCrudService<Course, Long> implements CourseService {

	@Inject
	private CourseDAO courseDAO;

	@Override
	protected JpaRepository<Course, Long> getDAO() {
		return courseDAO;
	}

}
