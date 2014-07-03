package fr.iut.csid.empower.elearning.web.controller.domain.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.web.controller.domain.AbstractDomainController;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.AdministratorResourceAssembler;
import fr.iut.csid.empower.elearning.web.service.AdministratorService;
import fr.iut.csid.empower.elearning.web.service.DTOSupport;

@Controller
@RequestMapping("/administrators")
public class AdministratorController extends AbstractDomainController<Administrator, Long, UserDTO> {

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
	protected DTOSupport<Administrator, Long, UserDTO> getDTOSupport() {
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
