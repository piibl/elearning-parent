package fr.iut.csid.empower.elearning.web.controller.entity.course;

import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.dto.impl.CourseDTO;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.TeacherService;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseResourceAssembler;

@Controller
@RequestMapping("/courses")
public class CourseController extends AbstractEntityController<Course, Long, CourseDTO> {

	private String mainView = "display/courses :: display-courses";
	private String detailsView = "display/courses :: display-details";
	private String entitiesAttributeName = "courses";
	private String singleEntityAttributeName = "course";
	private String addForm = "forms/add-forms :: add-course-form";
	private String editForm = "forms/edit-forms :: edit-course-form";

	@Inject
	private CourseService courseService;
	@Inject
	private TeacherService teacherService;

	@Inject
	private CourseResourceAssembler courseResourceAssembler;

	/**
	 * Retourne le formulaire de saisie d'une nouvelle instance TODO Sécurité : seul les enseignats peuvent créer un cours
	 * 
	 * @param model
	 * @return
	 */
	@Override
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getAddForm(Model model, @AuthenticationPrincipal User user) {
		// Quick'n'dirty encore
		// TODO nullcheck, sait-on jamais.... hein.
		model.addAttribute("currentTeacher", teacherService.findByLogin(user.getUsername()));
		return getAddFormPath();
	}

	@RequestMapping("/search/{teacherId}")
	public String getCoursesByTeacher(Model model, @PathVariable Long teacherId) {

		List<Course> courses = courseService.findByTeacher(teacherId);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = getResourceAssembler().toResource(courses);
		model.addAttribute(getEntitiesAtributeName(), coursesResources);
		// Rendu partiel
		model.addAttribute("partial", true);
		return getBaseView();
	}

	@Override
	protected CrudService<Course, Long, CourseDTO> getCrudService() {
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

	@Override
	protected String getEditFormPath() {
		return editForm;
	}
}
