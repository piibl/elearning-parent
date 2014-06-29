package fr.iut.csid.empower.elearning.web.controller.domain.news;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.service.NewsService;

@Controller
@RequestMapping("*/news")
public class NewsController {

	private String mainView = "display/news :: display-news";
	private String detailsView = "display/news :: display-details";
	private String entitiesAttributeName = "news";
	private String singleEntityAttributeName = "singleNews";

	@Inject
	private NewsService newsService;

	// @Inject
	// private CourseResourceAssembler courseResourceAssembler;

	/**
	 * Fonctionnalité d'inscription à un cours.<br/>
	 * Celle-ci se base sur le postulat que l'utilisateur appelant est un étudiant. <br/>
	 * TODO cette fonction ne devrait pas être assumée par un controller de domaine.... Refactor à faire, conception à peaufiner
	 * 
	 * @param model
	 * @param courseId
	 * @param user
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getNotifications(Model model) {
		model.addAttribute(getEntitiesAtributeName(), newsService.getNews());
		return getBaseView();
	}

	// protected BatchResourceAssembler<Course, Resource<Course>> getResourceAssembler() {
	// return courseResourceAssembler;
	// }

	protected String getBaseView() {
		return mainView;
	}

	protected String getEntitiesAtributeName() {
		return entitiesAttributeName;
	}

	protected String getSingleEntityAtributeName() {
		return singleEntityAttributeName;
	}

	protected String getDetailsView() {
		return detailsView;
	}

}
