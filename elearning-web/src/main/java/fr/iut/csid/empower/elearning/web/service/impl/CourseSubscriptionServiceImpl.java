package fr.iut.csid.empower.elearning.web.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionType;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionRepository;
import fr.iut.csid.empower.elearning.web.service.CourseSubscriptionService;

@Named
public class CourseSubscriptionServiceImpl implements CourseSubscriptionService {

	@Inject
	private CourseRepository courseRepository;
	@Inject
	private CourseSubscriptionRepository courseSubscriptionRepository;

	@Override
	public CourseSubscription subscribe(Student student, Long courseId) {
		Course course = courseRepository.findOne(courseId);
		if (course != null) {
			// TODO A refactorer quand les deux types d'inscriptions seront implémentés
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
			return label;
		}
		throw new CourseNotExistsException();

	}

}
