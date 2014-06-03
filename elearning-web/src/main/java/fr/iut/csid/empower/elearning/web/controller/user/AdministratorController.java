package fr.iut.csid.empower.elearning.web.controller.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.service.AdministratorService;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.assembler.AdministratorResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/administrators")
public class AdministratorController extends AbstractEntityController<Administrator, Long> {

	private String mainView = "administrators";
	private String entitiesAttributeName = "administrators";
	private String singleEntityAttributeName = "administrator";

	@Inject
	private AdministratorService administratorService;

	@Inject
	private AdministratorResourceAssembler administratorResourceAssembler;

	// @Inject
	// private ControllerLinkBuilderFactory linkBuilderFactory;

	// private Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Override
	protected CrudService<Administrator, Long> getCrudService() {
		return administratorService;
	}

	@Override
	protected BatchResourceAssembler<Administrator, Resource<Administrator>> getResourceAssembler() {
		return administratorResourceAssembler;
	}

	@Override
	protected String getBaseViewPage() {
		return mainView;
	}

	@Override
	protected String getEntitiesAtributeName() {
		return entitiesAttributeName;
	}

	@Override
	protected String getSingleEntityAtributeName() {
		return singleEntityAttributeName;
	}
}
