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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.web.controller.entity.user.validator.StudentValidator;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

@Controller
public class StudentRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(StudentRegistrationController.class);

	private static final String registrationPath = "fragment/registration :: registration-form";

	@Inject
	private StudentService studentService;
	/**
	 * Validateur objet student
	 */
	@Inject
	private StudentValidator studentValidator;

	@ModelAttribute("studentStructure")
	public Student getStudent() {
		return new Student();
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(studentValidator);
	}

	@RequestMapping(value = { "/registration" })
	public String getRegistrationPage(Model model) {
		// Retourne le formulaire d'inscription
		return registrationPath;
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String registerStudent(@Valid Student student, BindingResult result, Model model) {
		logger.info("POST Request on registration form");
		if (result.hasErrors()) {
			// Cas impossible, pas de validation pour l'instant ;)

		}
		/***
		 * TODO IMPLEMENTATION SUCCESS !!
		 */
		model.addAttribute("loginNewUser", studentService.save(student).getLogin());
		// Retourne la page d'accueil
		return PathFragment.HOME.getPath();
	}

}
