package fr.iut.csid.empower.elearning.core.domain.notification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.iut.csid.empower.elearning.core.domain.user.User;


/**
 * Classe des entités notifications
 */
@Entity
@Table(name="NOTIFICATION")
public class Notification{
	
	//ATTRIBUTS
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NotificationSeq")
	@SequenceGenerator(name = "NotificationSeq", sequenceName = "NOTIFICATION_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "NOTIFICATION_ID", nullable = false)
	@Id
	private Long id;
	
	@Column(name = "SUBJECT")
	private String notificationSubject;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User notificationReceiver;
	
	@Column(name = "BODY")
	private String notificationBody;
	
	
	//CONSTRUCTEURS
	public Notification(){
		
	}
	
	
	public Notification(String notificationSubject, User notificationReceiver,
			String notificationBody) {
		super();
		this.notificationSubject = notificationSubject;
		this.notificationReceiver = notificationReceiver;
		this.notificationBody = notificationBody;
	}




	public User getNotificationReceiver() {
		return notificationReceiver;
	}




	public void setNotificationReceiver(User notificationReceiver) {
		this.notificationReceiver = notificationReceiver;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public void setNotificationSubject(String notificationSubject) {
		this.notificationSubject = notificationSubject;
	}




	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}




	//ACCESSEURS
	public Long getId() {
		return id;
	}

	public String getNotificationSubject() {
		return notificationSubject;
	}

	public String getNotificationBody() {
		return notificationBody;
	}
	
	
	//METHODES
	
	
}
