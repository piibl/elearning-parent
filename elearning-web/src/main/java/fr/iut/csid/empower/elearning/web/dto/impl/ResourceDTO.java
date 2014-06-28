package fr.iut.csid.empower.elearning.web.dto.impl;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import fr.iut.csid.empower.elearning.core.reference.ResourceType;
import fr.iut.csid.empower.elearning.web.dto.OwnedDTO;

/**
 * Objet tampon servant à la conversion Json -> Objet
 */
public class ResourceDTO implements Serializable, OwnedDTO<Long> {

	/**
	 * Type de la ressource
	 */
	private ResourceType resourceType;
	/**
	 * Nom de la ressource
	 */
	private String resourceName;

	/**
	 * Fichier uploadé comme ressource
	 */
	private MultipartFile file;
	/**
	 * Identifiant du chapitre propriétaire de la ressource
	 */
	private Long ownerId;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerCourseId) {
		this.ownerId = ownerCourseId;
	}

	public ResourceType getResourcetype() {
		return resourceType;
	}

	public String getResourceName() {
		return resourceName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setResourcetype(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
