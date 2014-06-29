package fr.iut.csid.empower.elearning.core.service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, X extends Serializable> {

	/**
	 * Sauvegarde une entité
	 * 
	 * @param entityToSave
	 * @return
	 */
	public T save(T entityToSave);

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
