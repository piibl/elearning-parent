package fr.iut.csid.empower.elearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public String getAdminPage(Model model) {
		// Retourne la page admin
		return "pages/admin";
	}
}
