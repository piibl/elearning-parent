package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.CourseSessionRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.CourseSessionDTO;
import fr.iut.csid.empower.elearning.web.service.CourseSessionService;

/**
 * 
 */
@Named
public class CourseSessionServiceImpl extends AbstractCrudService<CourseSession, Long> implements CourseSessionService {

	@Inject
	private CourseSessionRepository courseSessionRepository;
	@Inject
	private CourseRepository courseRepository;

	@Override
	public CourseSession createFromDTO(CourseSessionDTO entityDTO) {
		Course ownerCourse = courseRepository.findOne(Long.valueOf(entityDTO.getOwnerId()));
		if (ownerCourse != null) {
			return courseSessionRepository.save(new CourseSession(entityDTO.getLabel(), ownerCourse, courseSessionRepository.countByOwnerCourse(ownerCourse) + 1,
					null, null, entityDTO.getSummary()));
			// entityDTO.getStartDate(), entityDTO.getEndDate()));
		}
		return null;
	}

	@Override
	public CourseSession saveFromDTO(CourseSessionDTO entityDTO, Long id) {
		CourseSession courseSession = courseSessionRepository.findOne(id);
		if (courseSession != null) {
			// TODO update autres champs ?
			courseSession.setLabel(entityDTO.getLabel());
			// courseSession.setStartDate(entityDTO.getStartDate());
			// courseSession.setEndDate(entityDTO.getEndDate());
			return courseSessionRepository.save(courseSession);
		}
		// TODO erreur globale
		throw new CourseNotExistsException();
	}

	@Override
	protected JpaRepository<CourseSession, Long> getRepository() {
		return courseSessionRepository;
	}

	@Override
	public List<CourseSession> findByOwner(Long ownerEntityId) {
		Course course = courseRepository.findOne(ownerEntityId);
		if (course != null) {
			return courseSessionRepository.findByOwnerCourseOrderBySessionRankAsc(course);
		}
		throw new CourseNotExistsException();
	}

}
