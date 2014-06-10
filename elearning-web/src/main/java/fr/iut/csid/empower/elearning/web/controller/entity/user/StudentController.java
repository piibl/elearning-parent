package fr.iut.csid.empower.elearning.web.controller.entity.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.StudentResourceAssembler;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractEntityController<Student, Long, UserDTO> {

	private String mainView = "display/students :: display-students";
	private String detailsView = "display/students :: display-details";
	private String entitiesAttributeName = "students";
	private String singleEntityAttributeName = "student";
	private String addForm = "forms/add-forms :: add-student-form";
	private String editForm = "forms/edit-forms :: edit-student-form";

	@Inject
	private StudentService studentService;

	@Inject
	private StudentResourceAssembler studentResourceAssembler;

	@Override
	protected CrudService<Student, Long, UserDTO> getCrudService() {
		return studentService;
	}

	@Override
	protected BatchResourceAssembler<Student, Resource<Student>> getResourceAssembler() {
		return studentResourceAssembler;
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
