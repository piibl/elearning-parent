package fr.iut.csid.empower.elearning.web.controller.domain.course.session;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.controller.domain.AbstractOwnedDomainController;
import fr.iut.csid.empower.elearning.web.controller.domain.course.CourseController;
import fr.iut.csid.empower.elearning.web.dto.impl.CourseSessionDTO;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseSessionResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.breadcrumb.BreadcrumbLink;
import fr.iut.csid.empower.elearning.web.reference.Relation;
import fr.iut.csid.empower.elearning.web.service.CourseService;
import fr.iut.csid.empower.elearning.web.service.CourseSessionService;
import fr.iut.csid.empower.elearning.web.service.OwnedEntityManagerService;
import fr.iut.csid.empower.elearning.web.service.ResourceStorageService;

@Controller
@RequestMapping("/courses/{ownerEntityId}/sessions")
public class CourseSessionController extends AbstractOwnedDomainController<CourseSession, Long, CourseSessionDTO> {

	private String mainView = "display/sessions :: display-sessions";
	private String detailsView = "display/sessions :: display-details";
	private String entitiesAttributeName = "courseSessions";
	private String singleEntityAttributeName = "courseSession";
	private String addForm = "forms/add-forms :: add-session-form";
	private String editForm = "forms/edit-forms :: edit-session-form";

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	@Inject
	private CourseSessionService courseSessionService;
	@Inject
	private CourseService courseService;
	@Inject
	private ResourceStorageService storageService;

	@Inject
	private CourseSessionResourceAssembler courseSessionResourceAssembler;

	@ModelAttribute("ownerCourseLabel")
	public String getCourseLabel(@PathVariable Long ownerEntityId) {
		return courseService.find(ownerEntityId).getLabel();
	}

	protected BatchResourceAssembler<CourseSession, Resource<CourseSession>> getResourceAssembler() {
		return courseSessionResourceAssembler;
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
		return courseService;
	}

	@Override
	protected String getAddOwnedEntityLink(Long ownerId) {
		return linkBuilderFactory.linkTo(CourseController.class).slash(ownerId).slash(Relation.SESSIONS.getName()).slash(Relation.NEW.getName())
				.withRel("new").getHref();
	}

	@Override
	protected OwnedEntityManagerService<CourseSession, Long, CourseSessionDTO> getCrudService() {
		return courseSessionService;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model, @PathVariable Long ownerEntityId) {
		// Ajout des données du fil d'arianne
		BreadcrumbLink[] breadcrumbLinks = {
				new BreadcrumbLink("Vos cours", linkBuilderFactory.linkTo(CourseController.class).withSelfRel().getHref(), "displayLink"),
				new BreadcrumbLink(courseService.find(ownerEntityId).getLabel(), "#", "active") };
		model.addAttribute("breadcrumbLinks", breadcrumbLinks);
		return super.getAll(model, ownerEntityId);
	}

	@Override
	public String getEntity(@PathVariable Long ownerEntityId, @PathVariable Long entityId, Model model) {
		// Ajout des données du fil d'arianne
		BreadcrumbLink[] breadcrumbLinks = {
				new BreadcrumbLink("Vos cours", linkBuilderFactory.linkTo(CourseController.class).withSelfRel().getHref(), "displayLink"),
				new BreadcrumbLink(courseService.find(ownerEntityId).getLabel(), "#"),
				new BreadcrumbLink("Chapitres du cours", linkBuilderFactory.linkTo(CourseController.class).slash(ownerEntityId)
						.slash(Relation.SESSIONS.getName()).withSelfRel().getHref(), "displayLink"),
				new BreadcrumbLink(courseSessionService.find(entityId).getLabel(), "#") };
		model.addAttribute("breadcrumbLinks", breadcrumbLinks);
		return super.getEntity(ownerEntityId, entityId, model);
	}
}
