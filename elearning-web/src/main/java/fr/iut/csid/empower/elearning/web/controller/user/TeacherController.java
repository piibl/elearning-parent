package fr.iut.csid.empower.elearning.web.controller.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.TeacherService;
import fr.iut.csid.empower.elearning.web.assembler.TeacherResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends AbstractEntityController<Teacher, Long> {

	private String mainView = "teachers";
	private String entitiesAttributeName = "teachers";
	private String singleEntityAttributeName = "teacher";

	@Inject
	private TeacherService teacherService;

	@Inject
	private TeacherResourceAssembler teacherResourceAssembler;

	// @Inject
	// private ControllerLinkBuilderFactory linkBuilderFactory;

	// private Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Override
	protected CrudService<Teacher, Long> getCrudService() {
		return teacherService;
	}

	@Override
	protected BatchResourceAssembler<Teacher, Resource<Teacher>> getResourceAssembler() {
		return teacherResourceAssembler;
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
