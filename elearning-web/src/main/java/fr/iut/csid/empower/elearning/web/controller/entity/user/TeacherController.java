package fr.iut.csid.empower.elearning.web.controller.entity.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.dto.UserDTO;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.TeacherService;
import fr.iut.csid.empower.elearning.web.assembler.TeacherResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends AbstractEntityController<Teacher, Long, UserDTO> {

	private String mainView = "display/teachers :: display-teachers";
	private String detailsView = "display/teachers :: display-details";
	private String entitiesAttributeName = "teachers";
	private String singleEntityAttributeName = "teacher";
	private String addForm = "fragment/add-forms :: add-teacher-form";

	@Inject
	private TeacherService teacherService;

	@Inject
	private TeacherResourceAssembler teacherResourceAssembler;

	@Override
	protected CrudService<Teacher, Long, UserDTO> getCrudService() {
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
}
