package fr.iut.csid.empower.elearning.web.controller.dashboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;
import fr.iut.csid.empower.elearning.web.service.StudentService;

@Controller
@RequestMapping("/dashboard/student")
public class StudentDashboardController extends AbstractDashboardController {

	/**
	 * Template d'affichage des cours auquels l'étudiant est inscrit
	 */
	private String subscribedCoursesView = "dashboards/display/student/student-courses :: display-subscribed-courses";
	/**
	 * Template d'affichage des cours auquels l'étudiant n'est pas inscrit
	 */
	private String notSubscribedCoursesView = "display/courses :: display-courses";

	// Services
	@Inject
	private StudentService studentService;

	// Assembler
	@Inject
	private CourseResourceAssembler courseResourceAssembler;

	/**
	 * Retourne le lien vers les cours auquels a souscrit l'étudiant
	 * 
	 * @param user
	 * @return
	 */
	@ModelAttribute("subscribedCoursesLink")
	public Link getSubscribedCoursesLink(@AuthenticationPrincipal User user) {
		// Recherche de l'utilisateur
		Student student = studentService.findByLogin(user.getUsername());
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return linkBuilderFactory.linkTo(getConcreteClass()).slash(student.getId()).slash(PathFragment.COURSES.getPath())
				.withRel(Relation.COURSES.getName());
	}

	/**
	 * Retourne le lien vers les cours auquels l'étudiant n'est pas inscrit
	 * 
	 * @param principal
	 * @return
	 */
	@ModelAttribute("othersAvailablesCoursesLink")
	public Link getAvailableCoursesLink(@AuthenticationPrincipal User user) {
		// Recherche de l'utilisateur
		Student student = studentService.findByLogin(user.getUsername());
		return linkBuilderFactory.linkTo(getConcreteClass()).slash(student.getId()).slash(PathFragment.COURSES.getPath())
				.slash(PathFragment.NOTSUBSCRIBED.getPath()).withRel(Relation.NOTSUBSCRIBED.getName());
	}

	/**
	 * Retourne les cours auxquels l'étudiant est inscrit
	 * 
	 * @param model
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/{studentId}/courses", method = RequestMethod.GET)
	public String getSubscribedCourses(Model model, @PathVariable Long studentId) {
		List<Course> courses = studentService.findSubscribedCourses(studentId);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
		return subscribedCoursesView;
	}

	/**
	 * Retourne les cours auxquels l'étudiant n'est pas inscrit
	 * 
	 * @param model
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/{studentId}/courses/not-subscribed", method = RequestMethod.GET)
	public String getUnsubscribedCourses(Model model, @PathVariable Long studentId) {
		List<Course> courses = studentService.findUnsubscribedCourses(studentId);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
		return notSubscribedCoursesView;
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
