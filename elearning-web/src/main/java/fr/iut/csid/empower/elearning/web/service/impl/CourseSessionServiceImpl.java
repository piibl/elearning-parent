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
import fr.iut.csid.empower.elearning.web.service.NotificationService;

/**
 * 
 */
@Named
public class CourseSessionServiceImpl extends AbstractCrudService<CourseSession, Long> implements CourseSessionService {

	@Inject
	private CourseSessionRepository courseSessionRepository;
	@Inject
	private CourseRepository courseRepository;
	@Inject
	private NotificationService notificationService;

	@Override
	public CourseSession createFromDTO(CourseSessionDTO entityDTO) {
		Course ownerCourse = courseRepository.findOne(Long.valueOf(entityDTO.getOwnerId()));
		CourseSession courseSession = new CourseSession(entityDTO.getLabel(), ownerCourse,
				courseSessionRepository.countByOwnerCourse(ownerCourse) + 1, null, null, entityDTO.getSummary());
		if (ownerCourse != null) {
			courseSessionRepository.save(courseSession);

			// Notification des enseignants du cours
			notificationService.createNotificationListTeacher("Création de chapitre", ownerCourse, "Le chapitre " + courseSession.getLabel()
					+ " a été créé pour le cours " + ownerCourse.getLabel() + ".");

			// Notification des étudiants du cours
			notificationService.createNotificationListStudent("Création de chapitre", ownerCourse, "Le chapitre " + courseSession.getLabel()
					+ " a été créé pour le cours " + ownerCourse.getLabel() + ".");
			return courseSession;
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

	@Override
	public void delete(CourseSession courseSession) {
		super.delete(courseSession);

		Course ownerCourse = courseSession.getOwnerCourse();
		// Notification des enseignants du cours
		notificationService.createNotificationListTeacher("Suppression de chapitre", ownerCourse, "Le chapitre " + courseSession.getLabel()
				+ " a été supprimé pour le cours " + ownerCourse.getLabel() + ".");

		// Notification des étudiants du cours
		notificationService.createNotificationListStudent("Suppression de chapitre", ownerCourse, "Le chapitre " + courseSession.getLabel()
				+ " a été supprimé pour le cours " + ownerCourse.getLabel() + ".");
	}

	@Override
	public CourseSession findByCourseAndSessionRank(Long courseId, Long sessionRank) {
		Course course = courseRepository.findOne(courseId);
		return courseSessionRepository.findByOwnerCourseAndSessionRank(course, sessionRank);
	}
}
