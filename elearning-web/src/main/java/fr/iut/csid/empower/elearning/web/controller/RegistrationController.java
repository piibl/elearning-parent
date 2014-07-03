package fr.iut.csid.empower.elearning.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.service.StudentService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	// private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	private static final String registrationPath = "fragment/registration :: registration-form";
	private static final String registrationSuccessMessage = "fragment/alerts :: new-user-welcome";

	@Inject
	private StudentService studentService;

	@RequestMapping(method = RequestMethod.GET)
	public String getRegistrationPage(Model model) {
		// Retourne le formulaire d'inscription
		return registrationPath;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerStudent(@RequestBody UserDTO user, Model model) {
		model.addAttribute("loginNewUser", studentService.createFromDTO(user).getLogin());

		// Retourne la page d'accueil
		return registrationSuccessMessage;
	}
}
