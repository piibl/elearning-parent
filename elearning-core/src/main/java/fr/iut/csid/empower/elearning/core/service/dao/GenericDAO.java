package fr.iut.csid.empower.elearning.core.service.dao;

import java.util.List;

/**
 * TODO à implémenter
 * 
 * @author pblanchard
 * 
 */
public interface GenericDAO<T> {

	// ***********************************************
	// Méthodes READ
	// ***********************************************

	/**
	 * Retourne l'enregistrement d'une classe déterminé par son identifiant.
	 * 
	 * @param id
	 *            : Identifiant de l'enregistrement.
	 * @return <code>T</code>
	 */
	public T findById(Long id);

	/**
	 * Retourne tous les enregistrements d'une classe.
	 * 
	 * @return <code>List</code>
	 */
	public List<T> findAll();

	/**
	 * Retourne le nombre total d'enregistrements d'une classe.
	 * 
	 * @return <code>long</code>
	 */
	public long countAll();

	/**
	 * Insère un nouvel enregistrement en base.
	 * 
	 * @param e
	 *            : Enregistrement à insérer.
	 * @return <code>T</code>
	 */
	public T create(T e);

	/**
	 * Insère un nouvel enregistrement en base immédiatement.
	 * 
	 * @param e
	 *            : Enregistrement à insérer.
	 * @return <code>T</code>
	 */
	public T immediateCreate(T e);

	// ***********************************************
	// Méthodes UPDATE
	// ***********************************************

	/**
	 * Met à jour un enregistrement en base.
	 * 
	 * @param e
	 *            : Enregistrement à mettre à jour.
	 * @return <code>T</code>
	 */
	public T update(T e);

	/**
	 * Met à jour un enregistrement en base immédiatement.
	 * 
	 * @param e
	 *            : Enregistrement à mettre a jour.
	 * @return <code>T</code>
	 */
	public T immediateUpdate(T e);

	// ***********************************************
	// Méthode DETELE
	// ***********************************************

	/**
	 * Supprime un enregistrement en base.
	 * 
	 * @param e
	 *            : Enregistrement à supprimer.
	 */
	public void delete(T e);

	// ***********************************************
	// Autres méthodes de persistance
	// ***********************************************

	/**
	 * Flush l'enregistrement modifié.
	 */
	public void flush();

	/**
	 * Actualise l'enregistrement modifié.
	 * 
	 * @param e
	 *            : Enregistrement modifié.
	 */
	public void refresh(T e);

	/**
	 * Détache l'enregistrement managé.
	 * 
	 * @param e
	 *            : Enregistrement managé.
	 * @return <code>T</code>
	 */
	public T detach(T e);
}