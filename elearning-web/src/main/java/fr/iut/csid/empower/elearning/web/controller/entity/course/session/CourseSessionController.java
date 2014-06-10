package fr.iut.csid.empower.elearning.web.controller.entity.course.session;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.dto.impl.CourseSessionDTO;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.CourseSessionService;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.OwnedEntityCrudService;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractOwnedEntityController;
import fr.iut.csid.empower.elearning.web.controller.entity.course.CourseController;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseSessionResourceAssembler;
import fr.iut.csid.empower.elearning.web.reference.Relation;

@Controller
@RequestMapping("/courses/{ownerEntityId}/sessions")
public class CourseSessionController extends AbstractOwnedEntityController<CourseSession, Long, CourseSessionDTO> {

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
	private CourseSessionResourceAssembler courseSessionResourceAssembler;

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
	protected OwnedEntityCrudService<CourseSession, Long, CourseSessionDTO> getCrudService() {
		return courseSessionService;
	}
}
