package fr.iut.csid.empower.elearning.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
	public String getMainPage(Model model) {
		// Retourne la page d'accueil
		return "pages/main";
	}
}
