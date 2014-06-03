package fr.iut.csid.empower.elearning.web.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.web.controller.user.TeacherController;
import fr.iut.csid.empower.elearning.web.hateoas.AbstractResourceAssembler;
import fr.iut.csid.empower.elearning.web.hateoas.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

/**
 * Représente les ressources associés à un enseignant existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class TeacherResourceAssembler extends AbstractResourceAssembler<Teacher, Resource<Teacher>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public TeacherResourceAssembler() {

	}

	@Override
	public Resource<Teacher> toResource(Teacher teacher) {
		Assert.notNull(teacher);
		// Adressage d'un enseignant
		Link selfLink = linkBuilderFactory.linkTo(TeacherController.class).slash(teacher.getId()).withRel(Relation.SELF.getName());
		// Adressage de tous les enseignants
		Link studentsLink = linkBuilderFactory.linkTo(TeacherController.class).withRel(Relation.TEACHERS.getName());
		// Adressage pour la suppression d'un enseignant
		Link deleteLink = linkBuilderFactory.linkTo(TeacherController.class).slash(teacher.getId()).slash(PathFragment.DELETE.getName())
				.withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'un enseignant
		Link editLink = linkBuilderFactory.linkTo(TeacherController.class).slash(teacher.getId()).slash(PathFragment.EDIT.getName())
				.withRel(Relation.EDIT.getName());
		return new Resource<Teacher>(teacher, selfLink, studentsLink, deleteLink, editLink);
	}

}
