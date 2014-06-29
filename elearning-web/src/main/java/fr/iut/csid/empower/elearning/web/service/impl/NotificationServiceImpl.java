package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
}
