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

import fr.iut.csid.empower.elearning.core.dto.IDTO;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.hateoas.ControllerLinkBuilderFactory;

public abstract class AbstractEntityController<T, X extends Serializable, Y extends IDTO> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractEntityController.class);

	/**
	 * Constructeur de liens
	 */
	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	/**
	 * Retourne le service CRUD du type de l'entité cible
	 */
	protected abstract CrudService<T, X, Y> getCrudService();

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
	 * Retourne le nom de l'attribut associé à une seule entité du type cible
	 * 
	 * @return
	 */
	protected abstract String getAddFormPath();

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
		return getBaseView();
	}

	/**
	 * Retourne le formulaire de saisie d'une nouvelle instance
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getAddForm(Model model) {
		return getAddFormPath();
	}

	/**
	 * Soumission via json
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insertNewJSON(@RequestBody Y entityDTO, Model model) {
		logger.info("JSON submission");
		getCrudService().saveFromDTO(entityDTO);
		// Alimenter le modèle avec la liste mise à jour
		return getAll(model);
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
		// TODO Quick'n'dirty, j'aime !
		Resource<T> resource = getResourceAssembler().toResource(getCrudService().find(id));
		model.addAttribute(getSingleEntityAtributeName(), resource);
		logger.info("getDetails calls : [" + getDetailsView() + "]");
		return getDetailsView();
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
		// TODO externalisation / personnalisation selon entités
		// Message de suppression
		List<T> entities = getCrudService().findAll();
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<T>> entitiesResources = getResourceAssembler().toResource(entities);
		model.addAttribute(getEntitiesAtributeName(), entitiesResources);
		return getAll(model);
	}

}
