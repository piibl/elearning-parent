package fr.iut.csid.empower.elearning.web.controller.dashboard;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.service.TeacherService;
import fr.iut.csid.empower.elearning.web.controller.entity.course.CourseController;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

@Controller
@RequestMapping("/teacherdashboard")
public class TeacherDashboardController extends AbstractDashboardController {

	@Inject
	private TeacherService teacherService;

	@ModelAttribute("coursesAffiliationsLink")
	public Link getCoursesAffiliationsLink(Principal principal) {
		// Recherche de l'utilisateur
		Teacher teacher = teacherService.findByLogin(principal.getName());
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return linkBuilderFactory.linkTo(CourseController.class).slash(Relation.SEARCH.getName()).slash(teacher.getId()).withSelfRel();
	}

	@ModelAttribute("currentTeacher")
	public Teacher getCurrentTeacher(Principal principal) {
		// Quick'n'dirty encore, un art de vivre
		// TODO null check... au cas où, hein....
		return teacherService.findByLogin(principal.getName());
	}

	@ModelAttribute("coursesLink")
	public Link getCoursesLink() {
		return linkBuilderFactory.linkTo(CourseController.class).withSelfRel();
	}

	@ModelAttribute("newCourseLink")
	public Link getNewCourseLink() {
		return linkBuilderFactory.linkTo(CourseController.class).slash(Relation.NEW.getName()).withSelfRel();
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public String getDashboardPage(Model model, Principal principal) {
	// model.addAttribute("login", principal.getName());
	// model.addAttribute("dashboardLink", linkBuilderFactory.linkTo(getConcreteClass()).withSelfRel());
	// // Récupération de l'enseignant authentifié
	// // Teacher teacher = teacherService.findByLogin(principal.getName());
	// // String coursesAffiliationsLink = linkBuilderFactory.linkTo(CourseController.class).slash("?teacher=" + teacher.getId()).withSelfRel()
	// // .getHref();
	// // model.addAttribute("coursesAffiliationsLink", coursesAffiliationsLink);
	// // Retourne la page d'accueil
	// return getMainView();
	// }

	@Override
	protected String getMainView() {
		return PathFragment.TEACHER_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return TeacherDashboardController.class;
	}
}
