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
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.TeacherService;
import fr.iut.csid.empower.elearning.web.controller.entity.course.CourseController;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

@Controller
@RequestMapping("/teacherdashboard")
public class TeacherDashboardController extends AbstractDashboardController {

	/**
	 * TODO Externaliser les paths !!!!!!!!!
	 */
	private String mainViewpath = "display/courses :: display-courses";
	private String addFormPath = "forms/add-forms :: add-course-form";
	private String editForm = "forms/edit-forms :: edit-course-form";

	@Inject
	private TeacherService teacherService;
	@Inject
	private CourseService courseService;
	@Inject
	private CourseResourceAssembler courseResourceAssembler;

	@ModelAttribute("ownedCoursesLink")
	public Link getCoursesAffiliationsLink(@AuthenticationPrincipal User user) {
		// Recherche de l'utilisateur
		Teacher teacher = teacherService.findByLogin(user.getUsername());
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return linkBuilderFactory.linkTo(getConcreteClass()).slash(teacher.getId()).slash(PathFragment.COURSES.getPath())
				.withRel(Relation.COURSES.getName());
	}

	/**
	 * Retourne les cours de l'enseignant logué
	 * 
	 * @param model
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/{teacherId}/courses", method = RequestMethod.GET)
	public String getCoursesByTeacher(Model model, @PathVariable Long teacherId) {
		List<Course> courses = courseService.findByTeacher(teacherId);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
		model.addAttribute("newCourseLink", linkBuilderFactory.linkTo(getConcreteClass()).slash(teacherId).slash(PathFragment.COURSES.getPath())
				.slash(PathFragment.NEW.getPath()).withSelfRel());
		model.addAttribute("redirectLink", linkBuilderFactory.linkTo(getConcreteClass()).slash(teacherId).slash(PathFragment.COURSES.getPath())
				.withRel(Relation.COURSES.getName()));
		return mainViewpath;
	}

	/**
	 * Demande de formulaire de création
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/{teacherId}/courses/new", method = RequestMethod.GET)
	public String getAddForm(Model model, @PathVariable Long teacherId) {
		// Quick'n'dirty encore
		// TODO nullcheck, sait-on jamais.... hein.
		model.addAttribute("teacher", teacherService.find(teacherId));
		model.addAttribute("submitLink", linkBuilderFactory.linkTo(CourseController.class).withSelfRel());
		// Lien de redirection pour n'avoir que les cours de l'enseignant et non tous les cours
		model.addAttribute("redirectLink", linkBuilderFactory.linkTo(getConcreteClass()).slash(teacherId).slash(PathFragment.COURSES.getPath())
				.withRel(Relation.COURSES.getName()));
		return addFormPath;
	}

	/**
	 * TODO utilité ?
	 * 
	 * @param user
	 * @return
	 */
	@ModelAttribute("teacher")
	public Teacher getCurrentTeacher(@AuthenticationPrincipal User user) {
		// Quick'n'dirty encore, un art de vivre
		// TODO null check... au cas où, hein....
		return teacherService.findByLogin(user.getUsername());
	}

	@Override
	protected String getMainView() {
		return PathFragment.TEACHER_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return TeacherDashboardController.class;
	}
}
