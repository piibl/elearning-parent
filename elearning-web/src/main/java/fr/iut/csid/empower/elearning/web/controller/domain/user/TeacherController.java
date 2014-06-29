package fr.iut.csid.empower.elearning.web.controller.domain.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.controller.domain.AbstractDomainController;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.TeacherResourceAssembler;
import fr.iut.csid.empower.elearning.web.service.DTOSupport;
import fr.iut.csid.empower.elearning.web.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends AbstractDomainController<Teacher, Long, UserDTO> {

	private String mainView = "display/teachers :: display-teachers";
	private String detailsView = "display/teachers :: display-details";
	private String entitiesAttributeName = "teachers";
	private String singleEntityAttributeName = "teacher";
	private String addForm = "forms/add-forms :: add-teacher-form";
	private String editForm = "forms/edit-forms :: edit-teacher-form";

	@Inject
	private TeacherService teacherService;

	@Inject
	private TeacherResourceAssembler teacherResourceAssembler;

	@Override
	protected DTOSupport<Teacher, Long, UserDTO> getDTOSupport() {
		return teacherService;
	}

	@Override
	protected BatchResourceAssembler<Teacher, Resource<Teacher>> getResourceAssembler() {
		return teacherResourceAssembler;
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
