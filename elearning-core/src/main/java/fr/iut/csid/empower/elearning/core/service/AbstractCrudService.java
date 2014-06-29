package fr.iut.csid.empower.elearning.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implémentation abstraite des fonctionnalités CRUD des services<br/>
 * 
 * @param <T>
 *            : Entité cible du service
 * @param <X>
 *            : Type de l'ID de l'entité cible (Long, String, etc.)
 */
public abstract class AbstractCrudService<T, X extends Serializable> {

	/**
	 * Retourne l'instance du dao à utiliser pour effectuer les opérations
	 * 
	 * @return
	 */
	protected abstract JpaRepository<T, X> getRepository();

	/**
	 * Sauvegarde une entité en utilisant la transaction courante
	 * 
	 * @param entity
	 *            : entité à sauvegarder
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public T save(T entityToSave) {
		return getRepository().save(entityToSave);

	}

	/**
	 * Sauvegarde une entité en utilisant une transaction indépendante
	 * 
	 * @param journal
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public T immediateSave(T entityToSave) {
		return getRepository().save(entityToSave);

	}

	/**
	 * Supprime une entité
	 * 
	 * @param entityToDelete
	 *            : entité à supprimer
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T entityToDelete) {
		getRepository().delete(entityToDelete);
	}

	/**
	 * Retourne vrai si une entité avec l'id passé en paramètre existe, faux sinon
	 * 
	 * @param id
	 *            : id de l'entité dont l'existence doit être vérifiée
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean exists(X id) {
		return getRepository().exists(id);
	}

	/**
	 * Retourne l'entité correspondante à l'id passé en paramètre si elle existe, false sinon
	 * 
	 * @param id
	 *            : id de l'entité
	 * @return
	 */
	@Transactional(readOnly = true)
	public T find(X id) {
		return getRepository().findOne(id);
	}

	/**
	 * Retourne toutes les entités de ce type
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getRepository().findAll();
	}

}
