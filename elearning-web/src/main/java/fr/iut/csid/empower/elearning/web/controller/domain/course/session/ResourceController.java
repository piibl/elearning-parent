package fr.iut.csid.empower.elearning.web.controller.domain.course.session;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.reference.ResourceType;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.controller.domain.AbstractOwnedDomainController;
import fr.iut.csid.empower.elearning.web.controller.domain.course.CourseController;
import fr.iut.csid.empower.elearning.web.dto.impl.ResourceDTO;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.link.assembler.ResourceResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.breadcrumb.BreadcrumbLink;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;
import fr.iut.csid.empower.elearning.web.service.CourseService;
import fr.iut.csid.empower.elearning.web.service.CourseSessionService;
import fr.iut.csid.empower.elearning.web.service.OwnedEntityManagerService;
import fr.iut.csid.empower.elearning.web.service.ResourceService;

@Controller
@RequestMapping("courses/{courseId}/sessions/{ownerEntityId}/resources")
public class ResourceController extends
		AbstractOwnedDomainController<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource, Long, ResourceDTO> {

	private String mainView = "display/resources :: display-resources";
	private String detailsView = "display/resources :: display-details";
	private String entitiesAttributeName = "resources";
	private String singleEntityAttributeName = "resource";
	private String addForm = "forms/add-forms :: add-resource-form";
	private String editForm = "forms/edit-forms :: edit-resource-form";

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	@Inject
	private CourseService courseService;
	@Inject
	private CourseSessionService courseSessionService;
	@Inject
	private ResourceService resourceService;

	@Inject
	private ResourceResourceAssembler resourceResourceAssembler;

	@ModelAttribute("ownerSessionLabel")
	public String getCourseLabel(@PathVariable Long ownerEntityId) {
		return courseSessionService.find(ownerEntityId).getLabel();
	}

	protected BatchResourceAssembler<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource, Resource<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource>> getResourceAssembler() {
		return resourceResourceAssembler;
	}

	protected String getBaseView() {
		return mainView;
	}

	protected String getEntitiesAtributeName() {
		return entitiesAttributeName;
	}

	protected String getSingleEntityAtributeName() {
		return singleEntityAttributeName;
	}

	protected String getAddFormPath() {
		return addForm;
	}

	protected String getDetailsView() {
		return detailsView;
	}

	protected String getEditFormPath() {
		return editForm;
	}

	@Override
	protected CrudService getOwnerCrudService() {
		return courseSessionService;
	}

	@Override
	protected String getAddOwnedEntityLink(Long ownerId) {
		CourseSession session = courseSessionService.find(ownerId);
		return linkBuilderFactory.linkTo(CourseController.class).slash(session.getOwnerCourse().getId()).slash(PathFragment.SESSIONS.getPath())
				.slash(ownerId).slash(PathFragment.RESOURCES.getPath()).slash(PathFragment.NEW.getPath()).withRel("new").getHref();
	}

	@Override
	protected OwnedEntityManagerService<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource, Long, ResourceDTO> getCrudService() {
		return resourceService;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model, @PathVariable Long ownerEntityId) {
		// Ajout des données du fil d'arianne
		Long courseId = courseSessionService.find(ownerEntityId).getOwnerCourse().getId();
		BreadcrumbLink[] breadcrumbLinks = {
				new BreadcrumbLink("Vos cours", linkBuilderFactory.linkTo(CourseController.class).withSelfRel().getHref(), "displayLink"),
				new BreadcrumbLink(courseService.find(courseId).getLabel(), "#", "active"),
				new BreadcrumbLink("Chapitres du cours", linkBuilderFactory.linkTo(CourseController.class).slash(courseId)
						.slash(Relation.SESSIONS.getName()).withSelfRel().getHref(), "displayLink"),
				new BreadcrumbLink(courseSessionService.find(ownerEntityId).getLabel(), "#"),
				new BreadcrumbLink("Ressources", linkBuilderFactory.linkTo(CourseController.class).slash(courseId)
						.slash(PathFragment.SESSIONS.getPath()).slash(ownerEntityId).slash(PathFragment.RESOURCES.getPath()).withSelfRel().getHref(),
						"displayLink") };
		model.addAttribute("breadcrumbLinks", breadcrumbLinks);
		return super.getAll(model, ownerEntityId);
	}

	@Override
	public String getEntity(@PathVariable Long ownerEntityId, @PathVariable Long entityId, Model model) {
		// Aucun appel sur une ressource en tant que tel autorisé pour l'instant, fonction de download à postériori
		return null;
	}

	@Override
	public String getAddForm(Model model, @PathVariable Long ownerEntityId) {
		model.addAttribute("allResourcesTypes", ResourceType.values());
		return super.getAddForm(model, ownerEntityId);
	}
}
