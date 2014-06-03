package fr.iut.csid.empower.elearning.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.Resource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

public abstract class AbstractEntityController<T, X extends Serializable> {

	/**
	 * Retourne le service CRUD du type de l'entité cible
	 */
	protected abstract CrudService<T, X> getCrudService();

	/**
	 * Retourne l'assembleur de ressources associé au type de l'entité cible
	 * 
	 * @return
	 */
	protected abstract BatchResourceAssembler<T, Resource<T>> getResourceAssembler();

	/**
	 * Retourne le path de la vue principale du controller concret
	 * 
	 * @return
	 */
	protected abstract String getBaseViewPage();

	/**
	 * Retourne le nom de l'attribut associé à la liste de toutes les entités du type cible
	 * 
	 * @return
	 */
	protected abstract String getEntitiesAtributeName();

	/**
	 * Retourne le nom de l'attribut associé à une seule entité du type cible
	 * 
	 * @return
	 */
	protected abstract String getSingleEntityAtributeName();

	/**
	 * Vue globale, affiche tous les éléments du type de l'entité
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model) {
		List<T> entities = getCrudService().findAll();
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<T>> entitiesResources = getResourceAssembler().toResource(entities);
		model.addAttribute(getEntitiesAtributeName(), entitiesResources);
		return PathFragment.BASE.getName() + getBaseViewPage();
	}

	/**
	 * Crée une nouvelle entité <br/>
	 * Un validateur doit être implémenté par le controller concret si besoin
	 * 
	 * @param entity
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insertNew(@Valid T entity, BindingResult result, Model model) {
		if (result.hasErrors()) {
			/***
			 * TODO IMPLEMENTATION Validateur !!
			 */

			// Cas impossible, pas de validation pour l'instant ;)
		}
		getCrudService().save(entity);
		/***
		 * TODO IMPLEMENTATION SUCCESS !!
		 */
		// Retourne la vue de base
		return PathFragment.REDIRECT.getName() + getBaseViewPage();
	}

	/**
	 * Retourne l'entité correspondant à l'id cible
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
	public String getEntity(@PathVariable("entityId") X id, Model model) {
		model.addAttribute(getSingleEntityAtributeName(), getCrudService().find(id));
		return PathFragment.BASE.getName() + getBaseViewPage();
	}

	/**
	 * Supprime l'entité correspondant à l'id passé en paramètre
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}/delete", method = RequestMethod.GET)
	public String deleteEntity(@PathVariable("entityId") X id, Model model) {
		T entityTodelete = getCrudService().find(id);
		getCrudService().delete(entityTodelete);

		List<T> entities = getCrudService().findAll();
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<T>> entitiesResources = getResourceAssembler().toResource(entities);
		model.addAttribute(getEntitiesAtributeName(), entitiesResources);
		return PathFragment.REDIRECT.getName() + getBaseViewPage();
	}

}
