package fr.iut.csid.empower.elearning.web.link.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.web.controller.domain.course.CourseController;
import fr.iut.csid.empower.elearning.web.link.AbstractResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

/**
 * Représente les ressources associés à un cours existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class CourseResourceAssembler extends AbstractResourceAssembler<Course, Resource<Course>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public CourseResourceAssembler() {

	}

	@Override
	public Resource<Course> toResource(Course course) {
		Assert.notNull(course);
		// Adressage d'un cours
		Link selfLink = linkBuilderFactory.linkTo(CourseController.class).slash(course.getId()).withRel(Relation.SELF.getName());
		// Adressage de tous les cours
		Link coursesLink = linkBuilderFactory.linkTo(CourseController.class).withRel(Relation.COURSES.getName());
		// Adressage pour la suppression d'un cours
		Link deleteLink = linkBuilderFactory.linkTo(CourseController.class).slash(course.getId()).slash(PathFragment.DELETE.getPath())
				.withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'un cours
		Link editLink = linkBuilderFactory.linkTo(CourseController.class).slash(course.getId()).slash(PathFragment.EDIT.getPath())
				.withRel(Relation.EDIT.getName());
		// Adressage pour accéder aux sessions d'un cours
		Link sessionsLink = linkBuilderFactory.linkTo(CourseController.class).slash(course.getId()).slash(PathFragment.SESSIONS.getPath())
				.withRel(Relation.SESSIONS.getName());
		// TODO notes
		// TODO étudiants / profs rattachés
		return new Resource<Course>(course, selfLink, coursesLink, deleteLink, editLink, sessionsLink);
	}

}
