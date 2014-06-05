package fr.iut.csid.empower.elearning.web.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.web.controller.entity.course.CourseController;
import fr.iut.csid.empower.elearning.web.hateoas.AbstractResourceAssembler;
import fr.iut.csid.empower.elearning.web.hateoas.ControllerLinkBuilderFactory;
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
	public Resource<Course> toResource(Course administrator) {
		Assert.notNull(administrator);
		// Adressage d'un cours
		Link selfLink = linkBuilderFactory.linkTo(CourseController.class).slash(administrator.getId()).withRel(Relation.SELF.getName());
		// Adressage de tous les cours
		Link studentsLink = linkBuilderFactory.linkTo(CourseController.class).withRel(Relation.COURSES.getName());
		// Adressage pour la suppression d'un cours
		Link deleteLink = linkBuilderFactory.linkTo(CourseController.class).slash(administrator.getId()).slash(PathFragment.DELETE.getPath())
				.withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'un cours
		Link editLink = linkBuilderFactory.linkTo(CourseController.class).slash(administrator.getId()).slash(PathFragment.EDIT.getPath())
				.withRel(Relation.EDIT.getName());
		// TODO chapitres
		// TODO notes
		// TODO étudiants / profs rattachés
		return new Resource<Course>(administrator, selfLink, studentsLink, deleteLink, editLink);
	}

}
