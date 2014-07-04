package fr.iut.csid.empower.elearning.web.service;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.EndUser;
import fr.iut.csid.empower.elearning.web.dto.impl.NotificationDTO;

public interface NotificationService extends DTOSupport<Notification, Long, NotificationDTO> {

	public List<Notification> findByUser(EndUser endUser);

	public Notification createNotification(String notificationSubject, EndUser notificationReceiver, String notificationBody);

	public void createNotificationListTeacher(String notificationSubject, Course course, String notificationBody);

	public void createNotificationListStudent(String notificationSubject, Course course, String notificationBody);
}
