package fr.iut.csid.empower.elearning.core.service;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.User;

public interface NotificationService {
	
	public List<Notification> findByUser(User user);

	public Notification createNotification(String notificationSubject,
			User notificationReceiver, String notificationBody);
	
	
}
