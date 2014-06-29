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

import fr.iut.csid.empower.elearning.core.domain.user.EndUser;


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
	private String subject;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private EndUser receiver;
	
	@Column(name = "BODY")
	private String body;
	
	
	//CONSTRUCTEURS
	public Notification(){
		
	}
	
	
	public Notification(String notificationSubject, EndUser notificationReceiver,
			String notificationBody) {
		super();
		this.subject = notificationSubject;
		this.receiver = notificationReceiver;
		this.body = notificationBody;
	}




	public EndUser getReceiver() {
		return receiver;
	}




	public void setReceiver(EndUser notificationReceiver) {
		this.receiver = notificationReceiver;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public void setSubject(String subject) {
		this.subject = subject;
	}




	public void setBody(String body) {
		this.body = body;
	}




	//ACCESSEURS
	public Long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}
	
	
	//METHODES
	
	
}
