package fr.iut.csid.empower.elearning.web.controller.dashboard;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.web.controller.entity.course.CourseController;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

@Controller
@RequestMapping("/studentdashboard")
public class StudentDashboardController extends AbstractDashboardController {
	
	@Inject
	private StudentService studentService; 
	
	@ModelAttribute("availableCourses")
	public List<Course> getAvailableCoursesLink(Principal principal) {
		// Recherche de l'utilisateur
		Student student = studentService.findByLogin(principal.getName());
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return null;
	}
	@Override
	protected String getMainView() {
		return PathFragment.STUDENT_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return StudentDashboardController.class;
	}

}
