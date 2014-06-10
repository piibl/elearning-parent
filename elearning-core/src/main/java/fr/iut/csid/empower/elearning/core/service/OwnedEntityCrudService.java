package fr.iut.csid.empower.elearning.core.service;

import java.io.Serializable;
import java.util.List;

import fr.iut.csid.empower.elearning.core.dto.OwnedDTO;

public interface OwnedEntityCrudService<T, X extends Serializable, Y extends OwnedDTO<X>> extends CrudService<T, X, Y> {

	/**
	 * Retourne la liste des instances appartenant à l'entité
	 * 
	 * @return
	 */
	public List<T> findByOwner(X ownerEntityId);

}
