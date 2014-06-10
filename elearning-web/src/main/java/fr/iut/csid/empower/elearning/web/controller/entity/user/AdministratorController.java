package fr.iut.csid.empower.elearning.web.controller.entity.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.core.service.AdministratorService;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.AdministratorResourceAssembler;

@Controller
@RequestMapping("/administrators")
public class AdministratorController extends AbstractEntityController<Administrator, Long, UserDTO> {

	private String mainView = "display/administrators :: display-administrators";
	private String detailsView = "display/administrators :: display-details";
	private String entitiesAttributeName = "administrators";
	private String singleEntityAttributeName = "administrator";
	private String addForm = "forms/add-forms :: add-administrator-form";
	private String editForm = "forms/edit-forms :: edit-administrator-form";

	@Inject
	private AdministratorService administratorService;

	@Inject
	private AdministratorResourceAssembler administratorResourceAssembler;

	@Override
	protected CrudService<Administrator, Long, UserDTO> getCrudService() {
		return administratorService;
	}

	@Override
	protected BatchResourceAssembler<Administrator, Resource<Administrator>> getResourceAssembler() {
		return administratorResourceAssembler;
	}

	@Override
	protected String getBaseView() {
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

	@Override
	protected String getAddFormPath() {
		return addForm;
	}

	@Override
	protected String getDetailsView() {
		return detailsView;
	}

	@Override
	protected String getEditFormPath() {
		return editForm;
	}
}
