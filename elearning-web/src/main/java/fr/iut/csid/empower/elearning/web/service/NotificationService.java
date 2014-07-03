package fr.iut.csid.empower.elearning.web.service;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.EndUser;

public interface NotificationService {
	
	public List<Notification> findByUser(EndUser endUser);
	
	public Notification createNotification(String notificationSubject,
			EndUser notificationReceiver, String notificationBody);
	
	public void createNotificationListTeacher(String notificationSubject,
			List<CourseTeaching> courseTeachingList, String notificationBody);
	
	public void createNotificationListStudent(String notificationSubject,
			List<CourseSubscription> courseSubscriptionList, String notificationBody);
}
