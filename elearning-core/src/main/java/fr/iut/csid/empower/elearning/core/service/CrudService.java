package fr.iut.csid.empower.elearning.core.service;

import java.io.Serializable;
import java.util.List;

import fr.iut.csid.empower.elearning.core.dto.IDTO;

public interface CrudService<T, X extends Serializable, Y extends IDTO> {

	/**
	 * Sauvegarde une entité
	 * 
	 * @param entityToSave
	 * @return
	 */
	public T save(T entityToSave);

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

	/**
	 * Sauvegarde une entité en utilisant une transaction indépendante
	 * 
	 * @param journal
	 * @return
	 */
	public T immediateSave(T entityToSave);

	/**
	 * Supprime une entité
	 * 
	 * @param entityToDelete
	 *            : entité à supprimer
	 */
	public void delete(T entityToDelete);

	/**
	 * Retourne vrai si une entité avec l'id passé en paramètre existe, faux sinon
	 * 
	 * @param id
	 *            : id de l'entité dont l'existence doit être vérifiée
	 * @return
	 */
	public boolean exists(X id);

	/**
	 * Retourne l'entité correspondante à l'id passé en paramètre si elle existe, false sinon
	 * 
	 * @param id
	 *            : id de l'entité
	 * @return
	 */
	public T find(X id);

	/**
	 * Retourne toutes les entités de ce type
	 * 
	 * @return
	 */
	public List<T> findAll();
}
