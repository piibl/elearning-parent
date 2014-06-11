package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionType;
import fr.iut.csid.empower.elearning.core.service.CourseSubscriptionService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionDAO;

@Named
public class CourseSubscriptionServiceImpl implements CourseSubscriptionService {

	@Inject
	private CourseDAO courseDAO;
	@Inject
	private CourseSubscriptionDAO courseSubscriptionDAO;

	@Override
	public CourseSubscription subscribe(Student student, Long courseId) {
		Course course = courseDAO.findOne(courseId);
		if (course != null) {
			// TODO A refactorer quand les deux types d'inscriptions seront implémentés
			return courseSubscriptionDAO.save(new CourseSubscription(student, course, CourseSubscriptionType.PARTICIPATIVE));
		}
		throw new CourseNotExistsException();

	}

}
