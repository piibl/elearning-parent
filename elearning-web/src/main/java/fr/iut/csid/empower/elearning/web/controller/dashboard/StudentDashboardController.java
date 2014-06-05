package fr.iut.csid.empower.elearning.web.controller.dashboard;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

@Controller
@RequestMapping("/studentdashboard")
public class StudentDashboardController {

	@ModelAttribute("courses")
	public List<Resource<Course>> getStudentCourses() {
		// Liste vide
		// TODO à implémenter
		return new ArrayList<Resource<Course>>();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getDashboardPage(Model model, Principal principal) {
		model.addAttribute("login", principal.getName());
		// Retourne la page d'accueil
		return PathFragment.STUDENT_DASHBOARD.getPath();
	}
}
