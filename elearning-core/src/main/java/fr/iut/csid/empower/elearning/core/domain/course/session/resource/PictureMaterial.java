package fr.iut.csid.empower.elearning.core.domain.course.session.resource;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.reference.ResourceType;

/**
 * Support de cours, désigne ici un support écrit (pdf, doc, etc.)
 */
@Entity
@DiscriminatorValue("PM")
public class PictureMaterial extends SessionResource {

	public PictureMaterial() {

	}

	/**
	 * Constructeur
	 * 
	 * @param ownerSession
	 * @param name
	 * @param summary
	 */
	public PictureMaterial(CourseSession ownerSession, String resourceName, String summary) {
		super(ownerSession, resourceName, summary, ResourceType.PICTURE_MATERIAL);
	}

}
