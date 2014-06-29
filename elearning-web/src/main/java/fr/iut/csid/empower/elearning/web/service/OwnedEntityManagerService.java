package fr.iut.csid.empower.elearning.web.service;

import java.io.Serializable;
import java.util.List;

import fr.iut.csid.empower.elearning.web.dto.OwnedDTO;

public interface OwnedEntityManagerService<T, X extends Serializable, Y extends OwnedDTO<X>> extends DTOSupport<T, X, Y> {

	/**
	 * Retourne la liste des instances appartenant à l'entité
	 * 
	 * @return
	 */
	public List<T> findByOwner(X ownerEntityId);

}
