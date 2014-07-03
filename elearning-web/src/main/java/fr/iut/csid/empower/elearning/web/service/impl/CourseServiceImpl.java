package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseTeachingRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.CourseSessionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.CourseDTO;
import fr.iut.csid.empower.elearning.web.service.CourseService;
import fr.iut.csid.empower.elearning.web.service.NotificationService;

/**
 * 
 */
@Named
public class CourseServiceImpl extends AbstractCrudService<Course, Long> implements CourseService {

	@Inject
	private CourseRepository courseRepository;
	@Inject
	private CourseTeachingRepository courseTeachingRepository;
	@Inject
	private CourseSubscriptionRepository courseSubscriptionRepository;
	@Inject
	private CourseSessionRepository courseSessionRepository;
	@Inject
	private NotificationService notificationService;
	
	@Inject
	private TeacherRepository teacherRepository;

	@Override
	protected JpaRepository<Course, Long> getRepository() {
		return courseRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { UserNotExistsException.class, NumberFormatException.class })
	public Course createFromDTO(CourseDTO courseDTO) {
		// Création d'un cours et save pour obtenir un id
		Course course = new Course(courseDTO.getLabel(), courseDTO.getSummary());
		course = courseRepository.save(course);
		// Conversion de l'id du créateur en type Long
		Long ownerId = Long.valueOf(courseDTO.getOwnerId());
		// Récupération de l'enseignant créateur
		Teacher teacher = teacherRepository.findOne(ownerId);
		if (teacher != null) {
			CourseTeaching courseTeaching = new CourseTeaching(teacher, course);
			courseTeachingRepository.save(courseTeaching);
			//Notification de création du cours
			notificationService.createNotification("Création du cours " + course.getLabel(), teacher, "Le cours " + course.getLabel() + "  a été créé avec succès.");
			return course;
		} else {
			// Pas d'enseignant associé à cet id
			throw new UserNotExistsException();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Course saveFromDTO(CourseDTO entityDTO, Long id) {
		Course course = courseRepository.findOne(id);
		if (course != null) {
			// Pas de questions, on reporte tous les changements
			course.setLabel(entityDTO.getLabel());
			course.setSummary(entityDTO.getSummary());
			return courseRepository.save(course);
		} else
			throw new CourseNotExistsException();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Course course) {
		
		//Notification des enseignants du cours
		notificationService.createNotificationListTeacher("Suppression du cours", courseTeachingRepository.findByCourse(course), "Le cours " + course.getLabel() + "  a été supprimé avec succès.");
		//Notification des étudiants du cours
		notificationService.createNotificationListStudent("Suppression du cours", courseSubscriptionRepository.findByCourse(course), "Le cours " + course.getLabel() + "  a été supprimé avec succès.");
		
		// Suppression de toutes les affiliations à ce cours
		for (CourseTeaching courseTeaching : courseTeachingRepository.findByCourse(course)) {
			courseTeachingRepository.delete(courseTeaching.getId());
		}
		// Suppression de toutes les subscriptions
		for (CourseSubscription courseSubscription : courseSubscriptionRepository.findByCourse(course)) {
			courseSubscriptionRepository.delete(courseSubscription.getId());
		}
		// Suppression de toutes les sessions
		for (CourseSession courseSession : courseSessionRepository.findByOwnerCourseOrderBySessionRankAsc(course)) {
			courseSessionRepository.delete(courseSession.getId());
		}
		// Suppression du cours
		courseRepository.delete(course);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findByTeacher(Long teacherId) {
		List<Course> courses = new ArrayList<Course>();
		// Récupération de l'enseignant
		Teacher teacher = teacherRepository.findOne(teacherId);
		if (teacher != null) {
			// recherche de toutes les affilition de l'enseignant
			for (CourseTeaching courseTeaching : courseTeachingRepository.findByTeacher(teacher)) {
				if (courseRepository.exists(courseTeaching.getCourse().getId())) {
					courses.add(courseRepository.getOne(courseTeaching.getCourse().getId()));

				} else {
					// TODO ignorer les orphelins ?
					throw new CourseNotExistsException();
				}
			}
			return courses;
		} else {
			// Pas d'enseignant associé à cet id
			throw new UserNotExistsException();
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findEnrichedCoursesByTeacher(Long teacherId) {
		// TODO Auto-generated method stub
		return null;
	}
}
