package fr.iut.csid.empower.elearning.web.controller.domain.course.session;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import fr.iut.csid.empower.elearning.core.domain.course.session.resource.TextMaterial;
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
import fr.iut.csid.empower.elearning.web.service.ResourceStorageService;

@Controller
@RequestMapping("/courses/{courseId}/sessions/{ownerEntityId}/resources")
public class ResourceController extends
		AbstractOwnedDomainController<fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource, Long, ResourceDTO> {

	private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

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
	private ResourceStorageService storageService;

	@Inject
	private ResourceResourceAssembler resourceResourceAssembler;

	private static LinkedList<SessionResource> files = new LinkedList<SessionResource>();
	private TextMaterial resource = null;

	@ModelAttribute("ownerSessionLabel")
	public String getCourseLabel(@PathVariable Long ownerEntityId) {
		return courseSessionService.find(ownerEntityId).getLabel();
	}

	protected BatchResourceAssembler<SessionResource, Resource<SessionResource>> getResourceAssembler() {
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
	protected OwnedEntityManagerService<fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource, Long, ResourceDTO> getCrudService() {
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
		// ajout du lien de redirection pour chargement après requete ajax
		model.addAttribute("redirectLink", linkBuilderFactory.linkTo(CourseController.class).slash(courseId).slash(PathFragment.SESSIONS.getPath())
				.slash(ownerEntityId).slash(PathFragment.RESOURCES.getPath()).withSelfRel());
		return super.getAll(model, ownerEntityId);
	}

	@Override
	public String getEntity(@PathVariable Long ownerEntityId, @PathVariable Long entityId, Model model) {
		// Aucun appel sur une ressource en tant que tel autorisé pour l'instant, fonction de download à postériori
		return null;
	}

	@Override
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getAddForm(Model model, @PathVariable Long ownerEntityId) {
		model.addAttribute(
				"redirectLink",
				linkBuilderFactory.linkTo(CourseController.class).slash(courseSessionService.find(ownerEntityId).getOwnerCourse().getId())
						.slash(PathFragment.SESSIONS.getPath()).slash(ownerEntityId).slash(PathFragment.RESOURCES.getPath()).withSelfRel());
		return super.getAddForm(model, ownerEntityId);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	LinkedList<SessionResource> upload(MultipartHttpServletRequest request, HttpServletResponse response) {

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		String ownerIdAsString = request.getParameter("ownerId");
		if (ownerIdAsString != null) {
			// Conversion String vers Long
			Long ownerId = Long.valueOf(ownerIdAsString);
			MultipartFile mpf = null;

			// 2. get each file
			while (itr.hasNext()) {

				// 2.1 get next MultipartFile
				mpf = request.getFile(itr.next());

				// 2.2 if files > 10 remove the first from the list
				if (files.size() >= 10)
					files.pop();

				// 2.3 create new fileMeta
				resource = new TextMaterial(courseSessionService.find(ownerId), mpf.getOriginalFilename(), "");
				resource.setFileSize(mpf.getSize() / 1024 + " Kb");
				resource.setFileType(mpf.getContentType());

				try {
					storageService.save(mpf.getInputStream(), resource.getFileType(), resource.getName());
					resourceService.save(resource);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 2.4 add to files
				files.add(resource);
			}
			// result will be like this
			// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]

		}
		return files;

	}
}
