package fr.iut.csid.empower.elearning.core.service.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe abtraite des méthodes génériques des classes DAOs.
 * 
 * @param <T>
 *            : Entité.
 */
public abstract class AbstractGenericDAO<T> implements GenericDAO<T> {

	/**
	 * Logger pour DAO.<br/>
	 * TODO utilité ??
	 */
	protected static Logger logger = LoggerFactory
			.getLogger(AbstractGenericDAO.class);

	// ***********************************************
	// Attributs
	// ***********************************************

	/**
	 * Manager de l'entité.
	 */
	@PersistenceContext
	protected EntityManager em;

	/**
	 * Entité.
	 */
	protected Class<T> entity;

	// ***********************************************
	// Constructeur
	// ***********************************************

	/**
	 * Constructeur.
	 * 
	 * @param entity
	 *            : Entité.
	 */
	public AbstractGenericDAO(Class<T> entity) {
		super();
		this.entity = entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		List<T> resultList = em.createNamedQuery(getPrefix() + "findAll")
				.getResultList();
		return resultList;
	}

	@Override
	public long countAll() {
		Query query = em.createNamedQuery(getPrefix() + "countAll");

		try {
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return 0;
		}
	}

	// ***********************************************
	// Méthodes READ
	// ***********************************************
	public T findById(Long id) {
		return em.find(entity, id);
	}

	// ***********************************************
	// Méthodes CREATE
	// ***********************************************

	public T create(T e) {
		em.persist(e);
		em.flush();
		return e;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public T immediateCreate(T e) {
		em.persist(e);
		return e;
	}

	// ***********************************************
	// Méthodes UPDATE
	// ***********************************************

	public T update(T e) {
		e = em.merge(e);
		return e;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public T immediateUpdate(T e) {
		e = em.merge(e);
		return e;
	}

	// ***********************************************
	// Méthode DETELE
	// ***********************************************

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void delete(T e) {
		em.remove(em.merge(e));
	}

	// ***********************************************
	// Autres méthodes de persistance
	// ***********************************************

	public void flush() {
		em.flush();
	}

	public void refresh(T e) {
		em.refresh(e);
	}

	public T detach(T e) {
		em.detach(e);
		return e;
	}

	// ***********************************************
	// Méthodes utiles
	// ***********************************************

	/**
	 * Retourne le chemin complet de l'entité.
	 * 
	 * @return <code>String</code>
	 */
	protected String getPrefix() {
		return this.entity.getCanonicalName() + ".";
	}
}
