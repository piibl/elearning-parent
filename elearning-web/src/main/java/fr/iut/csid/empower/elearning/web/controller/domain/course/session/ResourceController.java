package fr.iut.csid.empower.elearning.web.controller.domain.course.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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

import com.mongodb.gridfs.GridFSDBFile;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource;
import fr.iut.csid.empower.elearning.web.controller.domain.AbstractOwnedDomainController;
import fr.iut.csid.empower.elearning.web.controller.domain.course.CourseController;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.link.assembler.SessionResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.breadcrumb.BreadcrumbLink;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;
import fr.iut.csid.empower.elearning.web.service.CourseService;
import fr.iut.csid.empower.elearning.web.service.CourseSessionService;
import fr.iut.csid.empower.elearning.web.service.ResourceService;

/**
 * Controller de ressources <br/>
 * Particulier car il n'étend pas {@link AbstractOwnedDomainController}
 * 
 * @author a547891
 */
@Controller
@RequestMapping("/courses/{courseId}/sessions/{ownerEntityId}/resources")
public class ResourceController {

	// private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

	/**
	 * Paths <br/>
	 * Pas de formulaire d'édition, pas de vue détaillée
	 */
	private String mainView = "display/resources :: display-resources";
	// private String detailsView = "display/resources :: display-details";
	private String entitiesAttributeName = "resources";
	// private String singleEntityAttributeName = "resource";
	private String addForm = "forms/add-forms :: add-resource-form";
	// private String editForm = "forms/edit-forms :: edit-resource-form";

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	@Inject
	private CourseService courseService;
	@Inject
	private CourseSessionService courseSessionService;
	@Inject
	private ResourceService resourceService;

	@Inject
	private SessionResourceAssembler sessionResourceAssembler;

	@ModelAttribute("ownerSessionLabel")
	public String getCourseLabel(@PathVariable Long ownerEntityId) {
		return courseSessionService.find(ownerEntityId).getLabel();
	}

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
		List<SessionResource> entities = resourceService.findByOwner(ownerEntityId);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<SessionResource>> entitiesResources = sessionResourceAssembler.toResource(entities);
		model.addAttribute(entitiesAttributeName, entitiesResources);
		model.addAttribute(
				"addOwnedEntityLink",
				linkBuilderFactory.linkTo(CourseController.class).slash(courseId).slash(PathFragment.SESSIONS.getPath()).slash(ownerEntityId)
						.slash(PathFragment.RESOURCES.getPath()).slash(PathFragment.NEW.getPath()).withRel("new").getHref());
		// Retourner la vue principale
		return mainView;
	}

	/**
	 * Permet de downloader une resource donnée
	 * 
	 * @param ownerEntityId
	 * @param resourceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
	public void getEntity(@PathVariable Long resourceId, HttpServletResponse response) {
		// Récupération du fichier
		GridFSDBFile file = resourceService.getPhysicalResource(resourceId);
		if (file != null) {
			try {
				response.setContentType(file.getContentType());
				response.setContentLength((new Long(file.getLength()).intValue()));
				response.setHeader("content-Disposition", "attachment; filename=" + resourceService.find(resourceId).getOriginalFilename());
				// Copie du fichier dans la réponse
				IOUtils.copyLarge(file.getInputStream(), response.getOutputStream());
			} catch (IOException ex) {
				throw new RuntimeException("IOError writing file to output stream");
			}
		}
	}

	/**
	 * Demande de formulaire d'ajout d'une ressource
	 * 
	 * @param model
	 * @param ownerEntityId
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getAddForm(Model model, @PathVariable Long courseId, @PathVariable Long ownerEntityId) {
		// Lien de redirection après soumission du formulaire
		model.addAttribute(
				"redirectLink",
				linkBuilderFactory.linkTo(CourseController.class).slash(courseSessionService.find(ownerEntityId).getOwnerCourse().getId())
						.slash(PathFragment.SESSIONS.getPath()).slash(ownerEntityId).slash(PathFragment.RESOURCES.getPath()).withSelfRel());
		model.addAttribute("ownerEntity", courseSessionService.find(ownerEntityId));
		model.addAttribute("submitLink", linkBuilderFactory.linkTo(CourseController.class).slash(courseId).slash(PathFragment.SESSIONS.getPath())
				.slash(ownerEntityId).slash(PathFragment.RESOURCES.getPath()).slash(PathFragment.NEW.getPath()).withRel("new"));
		return addForm;
	}

	/**
	 * Création d'une nouvelle ressource
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody
	List<SessionResource> upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
		List<SessionResource> resources = new ArrayList<SessionResource>();
		// Normalement, un seul fichier par soumission, mais sait-on jamais donc boucle préventive
		// Un fichier soumis = une ressource créée

		// Récupérer l'itérateur
		Iterator<String> itr = request.getFileNames();
		// Récupérer l'id du chapitre propriétaire
		String ownerIdAsString = request.getParameter("ownerId");
		if (ownerIdAsString != null) {

			// Conversion String vers Long
			Long ownerId = Long.valueOf(ownerIdAsString);
			MultipartFile file = null;

			// Pour chaque fichier associé à la requête d'upload
			while (itr.hasNext()) {

				// Récupérer le fichier
				file = request.getFile(itr.next());

				// Créer la ressource
				// Génération d'un UUID pour référencement "unique" en bdd mongo
				UUID uniqueFileId = UUID.randomUUID();
				SessionResource resource = new SessionResource(courseSessionService.find(ownerId), uniqueFileId.toString(),
						file.getOriginalFilename(), file.getContentType(), file.getSize() / 1024);
				// Ajouter le flux à la ressource pour une sauvegarde en base mongodb
				resource.setContentStream(file.getInputStream());
				resources.add(resourceService.save(resource));
			}
		}
		/**
		 * TODO A TRAITER CAS D'ERREURS
		 */
		return resources;

	}

	/**
	 * Supprime l'entité correspondant à l'id passé en paramètre
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{resourceId}/delete", method = RequestMethod.GET)
	public String deleteEntity(@PathVariable Long ownerEntityId, @PathVariable Long resourceId, Model model) {
		SessionResource resourceTodelete = resourceService.find(resourceId);
		resourceService.delete(resourceTodelete);
		return getAll(model, ownerEntityId);
	}

}
