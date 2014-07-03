package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.EndUser;
import fr.iut.csid.empower.elearning.core.service.dao.notification.NotificationRepository;
import fr.iut.csid.empower.elearning.web.service.NotificationService;

@Named
public class NotificationServiceImpl implements NotificationService {
	
	@Inject
	private NotificationRepository notificationRepository;

	@Override
	public List<Notification> findByUser(EndUser endUser) {
		
		return notificationRepository.findByReceiver(endUser);
	}

	@Override
	public Notification createNotification(String notificationSubject,
			EndUser notificationReceiver, String notificationBody) {
		Notification notification = new Notification(notificationSubject, notificationReceiver, notificationBody);
		return notificationRepository.save(notification);
	}

	@Override
	public void createNotificationListTeacher(String notificationSubject, List<CourseTeaching> courseTeachingList, String notificationBody) {
		for(CourseTeaching courseTeaching : courseTeachingList){
			createNotification(notificationSubject, courseTeaching.getTeacher(), notificationBody);
		}
	}

	@Override
	public void createNotificationListStudent(String notificationSubject, List<CourseSubscription> courseSubscriptionList, String notificationBody) {
		for(CourseSubscription courseSubscription : courseSubscriptionList){
			createNotification(notificationSubject, courseSubscription.getStudent(), notificationBody);
		}
	}
}
