package fr.iut.csid.empower.elearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.reference.PathFragment;

@Controller
public class HomeController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String getHomePage(Model model) {
		// Retourne la page d'accueil
		return PathFragment.HOME.getPath();
	}
}
