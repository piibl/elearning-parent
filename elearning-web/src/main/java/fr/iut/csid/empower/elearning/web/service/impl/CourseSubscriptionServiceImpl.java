package fr.iut.csid.empower.elearning.web.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionType;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionRepository;
import fr.iut.csid.empower.elearning.web.service.CourseSubscriptionService;
import fr.iut.csid.empower.elearning.web.service.NotificationService;

@Named
public class CourseSubscriptionServiceImpl extends AbstractCrudService<CourseSubscription, Long> implements CourseSubscriptionService {

	@Inject
	private CourseRepository courseRepository;
	@Inject
	private CourseSubscriptionRepository courseSubscriptionRepository;

	@Inject
	private NotificationService notificationService;

	@Override
	public CourseSubscription subscribe(Student student, Long courseId) {
		Course course = courseRepository.findOne(courseId);
		if (course != null) {
			// TODO A refactorer quand les deux types d'inscriptions seront implémentés

			// Notification aux professeurs du cours
			notificationService.createNotificationListTeacher("Inscription à un cours", course, "L'étudiant(e) " + student.getFirstName() + " "
					+ student.getLastName() + " s'est inscrit(e) au cours " + course.getLabel() + ".");
			return courseSubscriptionRepository.save(new CourseSubscription(student, course, CourseSubscriptionType.PARTICIPATIVE));
		}
		throw new CourseNotExistsException();

	}

	@Override
	public String unsubscribe(Student student, Long courseId) {
		Course course = courseRepository.findOne(courseId);
		if (course != null) {
			// TODO A refactorer quand les deux types d'inscriptions seront implémentés
			CourseSubscription subscription = courseSubscriptionRepository.findByStudentAndCourse(student, course);
			String label = subscription.getCourse().getLabel();
			courseSubscriptionRepository.delete(subscription);

			// Notification aux professeurs du cour
			notificationService.createNotificationListTeacher("Désinscription à un cours", course, "L'étudiant(e) " + student.getFirstName() + " "
					+ student.getLastName() + " s'est désinscrit(e) du cours " + course.getLabel() + ".");
			return label;
		}
		throw new CourseNotExistsException();

	}

	@Override
	protected JpaRepository<CourseSubscription, Long> getRepository() {
		return courseSubscriptionRepository;
	}

}
