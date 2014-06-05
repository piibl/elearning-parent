package fr.iut.csid.empower.elearning.web.controller.entity.user;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.service.AdministratorService;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.assembler.AdministratorResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.dto.UserDTO;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/administrators")
public class AdministratorController extends AbstractEntityController<Administrator, Long> {

	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

	private String mainView = "display/administrators :: display-administrators";
	private String detailsView = "display/administrators :: display-details";
	private String entitiesAttributeName = "administrators";
	private String singleEntityAttributeName = "administrator";
	private String addForm = "fragment/add-forms :: add-administrator-form";

	@Inject
	private AdministratorService administratorService;

	@Inject
	private AdministratorResourceAssembler administratorResourceAssembler;

	@ModelAttribute("administratorStructure")
	public Administrator getAdministratorStructure() {
		return new Administrator();
	}

	// @Inject
	// private ControllerLinkBuilderFactory linkBuilderFactory;

	// private Logger logger = LoggerFactory.getLogger(StudentController.class);

	/**
	 * Soumission via json
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insertNewJSON(@RequestBody UserDTO userDTO, Model model) {
		logger.info("JSON submission");
		Administrator administrator = new Administrator(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getLogin(), userDTO.getPassword(),
				userDTO.getEmail());
		logger.info("Register adminnistrator with credentials [" + administrator.getLogin() + "]");
		administratorService.save(administrator);
		// Alimenter le modèle avec la liste mise à jour
		return getAll(model);
	}

	@Override
	protected CrudService<Administrator, Long> getCrudService() {
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
}
