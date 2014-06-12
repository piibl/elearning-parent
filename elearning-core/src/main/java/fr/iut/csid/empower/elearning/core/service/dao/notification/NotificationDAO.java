package fr.iut.csid.empower.elearning.core.service.dao.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.User;

public interface NotificationDAO extends JpaRepository<Notification, Long> {

	public List<Notification> findByReceiver(User user);

}
