package fr.iut.csid.empower.elearning.web.service;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.EndUser;

public interface NotificationService {
	
	public List<Notification> findByUser(EndUser endUser);
	
}
