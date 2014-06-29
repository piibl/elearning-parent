package fr.iut.csid.empower.elearning.web.service;

import java.io.Serializable;

import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.dto.IDTO;

public interface DTOSupport<T, X extends Serializable, Y extends IDTO> extends CrudService<T, X> {

	/**
	 * Crée et sauvegarde une entité à partir de son DTO
	 * 
	 * @param entityDTO
	 * @return
	 */
	public T createFromDTO(Y entityDTO);

	/**
	 * met à jour et sauvegarde une entité à partir de son DTO
	 * 
	 * @param entityDTO
	 * @return
	 */
	public T saveFromDTO(Y entityDTO, X id);

}
