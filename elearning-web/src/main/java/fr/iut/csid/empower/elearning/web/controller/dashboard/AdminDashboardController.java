package fr.iut.csid.empower.elearning.web.controller.dashboard;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.web.controller.entity.user.AdministratorController;
import fr.iut.csid.empower.elearning.web.controller.entity.user.StudentController;
import fr.iut.csid.empower.elearning.web.controller.entity.user.TeacherController;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

@Controller
@RequestMapping("/dashboard/admin")
public class AdminDashboardController extends AbstractDashboardController {

	@ModelAttribute("administratorsLink")
	public Link getAdministratorsLink() {
		return linkBuilderFactory.linkTo(AdministratorController.class).withSelfRel();
	}

	@ModelAttribute("teachersLink")
	public Link getTeachersLink() {
		return linkBuilderFactory.linkTo(TeacherController.class).withSelfRel();
	}

	@ModelAttribute("studentsLink")
	public Link getStudentsLink() {
		return linkBuilderFactory.linkTo(StudentController.class).withSelfRel();
	}

	// @ModelAttribute("coursesLink")
	// public Link getCoursesLink() {
	// return linkBuilderFactory.linkTo(CourseController.class).withSelfRel();
	// }

	@Override
	protected String getMainView() {
		return PathFragment.ADMIN_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return AdminDashboardController.class;
	}

}
