package fr.iut.csid.empower.elearning.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.User;
import fr.iut.csid.empower.elearning.core.service.NotificationService;
import fr.iut.csid.empower.elearning.core.service.dao.notification.NotificationDAO;

@Named
public class NotificationServiceImpl implements NotificationService {
	
	@Inject
	private NotificationDAO notificationDAO;

	@Override
	public List<Notification> findByUser(User user) {
		
		return notificationDAO.findByReceiver(user);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public Notification createNotification(String notificationSubject, User notificationReceiver, String notificationBody) {
		Notification notification = new Notification(notificationSubject, notificationReceiver, notificationBody);
		return notificationDAO.save(notification);
	}
	
}