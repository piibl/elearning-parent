package fr.iut.csid.empower.elearning.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.dto.impl.CourseSessionDTO;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.service.CourseSessionService;
import fr.iut.csid.empower.elearning.core.service.NotificationService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseTeachingDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.CourseSessionDAO;

/**
 * 
 */
@Named
public class CourseSessionServiceImpl extends AbstractCrudService<CourseSession, Long> implements CourseSessionService {

	@Inject
	private CourseSessionDAO courseSessionDAO;
	@Inject
	private CourseDAO courseDAO;
	@Inject
	private CourseTeachingDAO courseTeachingDAO;
	@Inject
	private NotificationService notificationService;
	
	@Override
	public CourseSession createFromDTO(CourseSessionDTO entityDTO) {
		Course ownerCourse = courseDAO.findOne(Long.valueOf(entityDTO.getOwnerId()));
		CourseSession courseSession = new CourseSession(entityDTO.getLabel(), ownerCourse, courseSessionDAO.countByOwnerCourse(ownerCourse) + 1, null, null,
				entityDTO.getSummary());
		List<CourseTeaching> courseTeachingList = courseTeachingDAO.findByCourse(ownerCourse);
		if (ownerCourse != null) {
			courseSessionDAO.save(courseSession);
			for(CourseTeaching courseTeaching : courseTeachingList){
				notificationService.createNotification("Création de session", courseTeaching.getTeacher(),
						"La session " + courseSession.getLabel() + " a été créée pour le cours " + ownerCourse.getLabel() + ".");
			}
			return courseSession;
			// entityDTO.getStartDate(), entityDTO.getEndDate()));
		}
		return null;
	}

	@Override
	public CourseSession saveFromDTO(CourseSessionDTO entityDTO, Long id) {
		CourseSession courseSession = courseSessionDAO.findOne(id);
		if (courseSession != null) {
			// TODO update autres champs ?
			courseSession.setLabel(entityDTO.getLabel());
			// courseSession.setStartDate(entityDTO.getStartDate());
			// courseSession.setEndDate(entityDTO.getEndDate());
			return courseSessionDAO.save(courseSession);
		}
		// TODO erreur globale
		throw new CourseNotExistsException();
	}

	@Override
	protected JpaRepository<CourseSession, Long> getDAO() {
		return courseSessionDAO;
	}

	@Override
	public List<CourseSession> findByOwner(Long ownerEntityId) {
		Course course = courseDAO.findOne(ownerEntityId);
		if (course != null) {
			return courseSessionDAO.findByOwnerCourseOrderBySessionRankAsc(course);
		}
		throw new CourseNotExistsException();
	}
	
	@Override
	public void delete(CourseSession courseSession) {
		super.delete(courseSession);
		
		Course ownerCourse = courseSession.getOwnerCourse();
		List<CourseTeaching> courseTeachingList = courseTeachingDAO.findByCourse(ownerCourse);
		for(CourseTeaching courseTeaching : courseTeachingList){
			notificationService.createNotification("Suppression de session", courseTeaching.getTeacher(),
					"La session " + courseSession.getLabel() + " a été supprimée pour le cours " + ownerCourse.getLabel() + ".");
		}
	}
}
