package fr.iut.csid.empower.elearning.web.controller.entity.course;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/courses")
public class CourseController extends AbstractEntityController<Course, Long> {

	private String mainView = "courses";
	private String entitiesAttributeName = "courses";
	private String singleEntityAttributeName = "course";
	private String addForm = "fragment/add-forms :: add-course-form";

	@Inject
	private CourseService courseService;

	@Inject
	private CourseResourceAssembler courseResourceAssembler;
	private String detailsView;

	@ModelAttribute("courseStructure")
	public Course getCourseStructure() {
		return new Course();
	}

	// @Inject
	// private ControllerLinkBuilderFactory linkBuilderFactory;

	// private Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Override
	protected CrudService<Course, Long> getCrudService() {
		return courseService;
	}

	@Override
	protected BatchResourceAssembler<Course, Resource<Course>> getResourceAssembler() {
		return courseResourceAssembler;
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
