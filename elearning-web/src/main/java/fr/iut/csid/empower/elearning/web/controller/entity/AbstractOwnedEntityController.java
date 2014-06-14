package fr.iut.csid.empower.elearning.web.controller.entity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.dto.OwnedDTO;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.OwnedEntityCrudService;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;

public abstract class AbstractOwnedEntityController<T, X extends Serializable, Y extends OwnedDTO<X>> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractOwnedEntityController.class);

	/**
	 * Constructeur de liens
	 */
	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	/**
	 * Retourne le service CRUD du type de l'entité cible
	 */
	protected abstract OwnedEntityCrudService<T, X, Y> getCrudService();

	/**
	 * Retourne le service CRUD du type de l'entité propriétaire de l'entité cible
	 */
	protected abstract CrudService getOwnerCrudService();

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
	protected abstract String getBaseView();

	/**
	 * Retourne le path de la vue détaillée d'une instance
	 */
	protected abstract String getDetailsView();

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
	 * Retourne le path du formulaire d'ajout
	 * 
	 * @return
	 */
	protected abstract String getAddFormPath();

	/**
	 * Retourne le path du formulaire de modification
	 * 
	 * @return
	 */
	protected abstract String getEditFormPath();

	/**
	 * Retourne le lien permettant la création d'une nouvelle entité liée à l'entité propriétaire
	 */
	protected abstract String getAddOwnedEntityLink(X ownerId);

	/**
	 * Vue globale, affiche tous les éléments du type de l'entité pour un parent donné
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model, @PathVariable X ownerEntityId) {
		List<T> entities = getCrudService().findByOwner(ownerEntityId);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<T>> entitiesResources = getResourceAssembler().toResource(entities);
		System.out.println(getEntitiesAtributeName() + " " + entities.size());
		model.addAttribute(getEntitiesAtributeName(), entitiesResources);
		model.addAttribute("addOwnedEntityLink", getAddOwnedEntityLink(ownerEntityId));
		return getBaseView();
	}

	/**
	 * Retourne le formulaire de saisie d'une nouvelle instance <br/>
	 * Si recquiert l'utilisation du profil de l'utilisateur courant, surcharger la méthode dans la classe concrète.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getAddForm(Model model, @PathVariable X ownerEntityId) {
		model.addAttribute("ownerEntity", getOwnerCrudService().find(ownerEntityId));
		return getAddFormPath();
	}

	/**
	 * Demande de création
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insertNewJSON(@RequestBody Y entityDTO, Model model) {
		getCrudService().createFromDTO(entityDTO);
		// Alimenter le modèle avec la liste mise à jour
		return getAll(model, entityDTO.getOwnerId());
	}

	/**
	 * Retourne l'entité correspondant à l'id cible
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
	public String getEntity(@PathVariable X ownerEntityId, @PathVariable("entityId") X id, Model model) {
		// TODO Quick'n'dirty, j'aime !
		Resource<T> resource = getResourceAssembler().toResource(getCrudService().find(id));
		model.addAttribute(getSingleEntityAtributeName(), resource);
		return getDetailsView();
	}

	/**
	 * Retourne le formulaire d'édition associé à l'instance
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}/edit", method = RequestMethod.GET)
	public String editEntity(@PathVariable X ownerEntityId, @PathVariable("entityId") X id, Model model) {
		Resource<T> resource = getResourceAssembler().toResource(getCrudService().find(id));
		// TODO externalisation / personnalisation selon entités
		// Message de suppression
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		model.addAttribute(getSingleEntityAtributeName(), resource);
		model.addAttribute("ownerEntity", getOwnerCrudService().find(ownerEntityId));
		return getEditFormPath();
	}

	/**
	 * Demande de modification, format JSON
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/{entityId}/edit", method = RequestMethod.POST)
	public String updateJSON(@RequestBody Y entityDTO, @PathVariable("entityId") X id, Model model) {
		getCrudService().saveFromDTO(entityDTO, id);
		// Alimenter le modèle avec la liste mise à jour
		return getAll(model, entityDTO.getOwnerId());
	}

	/**
	 * Supprime l'entité correspondant à l'id passé en paramètre
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}/delete", method = RequestMethod.GET)
	public String deleteEntity(@PathVariable X ownerEntityId, @PathVariable("entityId") X id, Model model) {
		T entityTodelete = getCrudService().find(id);
		getCrudService().delete(entityTodelete);
		// TODO externalisation / personnalisation selon entités
		// Message de suppression
		List<T> entities = getCrudService().findAll();
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<T>> entitiesResources = getResourceAssembler().toResource(entities);
		model.addAttribute(getEntitiesAtributeName(), entitiesResources);
		return getAll(model, ownerEntityId);
	}
}
