package fr.iut.csid.empower.elearning.web.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.web.controller.user.StudentController;
import fr.iut.csid.empower.elearning.web.controller.user.validator.StudentValidator;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

/**
 * TODO migration vers {@link StudentController}
 */
@Controller
public class StudentRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(StudentRegistrationController.class);

	@Inject
	private StudentService studentService;
	/**
	 * Validateur objet student
	 */
	@Inject
	private StudentValidator studentValidator;

	@ModelAttribute("studentStructure")
	public Student getStudentStructure() {
		return new Student();
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(studentValidator);
	}

	@RequestMapping(value = { "/registration" })
	public String getRegistrationPage(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		// Si requete ajax
		if (requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false) {
			return "fragment/registration :: registration-form";
		}
		// Si pas requete Ajax, on ignore la demande
		return PathFragment.BASE.getName() + "main";
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String registerStudent(@Valid Student student, BindingResult result, Model model) {
		logger.info("POST Request on registration form");
		if (result.hasErrors()) {
			/***
			 * TODO IMPLEMENTATION Validateur !!
			 */

			// Cas impossible, pas de validation pour l'instant ;)
		}

		studentService.save(student);
		/***
		 * TODO IMPLEMENTATION SUCCESS !!
		 */
		// Retourne la page d'inscription au service
		return PathFragment.BASE.getName() + "main";
	}
}
