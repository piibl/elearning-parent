package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.EndUser;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseTeachingRepository;
import fr.iut.csid.empower.elearning.core.service.dao.notification.NotificationRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.NotificationDTO;
import fr.iut.csid.empower.elearning.web.service.NotificationService;

@Named
public class NotificationServiceImpl extends AbstractCrudService<Notification, Long> implements NotificationService {

	@Inject
	private NotificationRepository notificationRepository;
	@Inject
	private CourseTeachingRepository courseTeachingRepository;
	@Inject
	private CourseSubscriptionRepository courseSubscriptionRepository;

	@Override
	public List<Notification> findByUser(EndUser endUser) {

		return notificationRepository.findByReceiver(endUser);
	}

	@Override
	public Notification createNotification(String notificationSubject, EndUser notificationReceiver, String notificationBody) {
		Notification notification = new Notification(notificationSubject, notificationReceiver, notificationBody);
		return notificationRepository.save(notification);
	}

	@Override
	public void createNotificationListTeacher(String notificationSubject, Course course, String notificationBody) {
		for (CourseTeaching courseTeaching : courseTeachingRepository.findByCourse(course)) {
			createNotification(notificationSubject, courseTeaching.getTeacher(), notificationBody);
		}
	}

	@Override
	public void createNotificationListStudent(String notificationSubject, Course course, String notificationBody) {
		for (CourseSubscription courseSubscription : courseSubscriptionRepository.findByCourse(course)) {
			createNotification(notificationSubject, courseSubscription.getStudent(), notificationBody);
		}
	}

	@Override
	public Notification createFromDTO(NotificationDTO entityDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification saveFromDTO(NotificationDTO entityDTO, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JpaRepository<Notification, Long> getRepository() {
		return notificationRepository;
	}
}
