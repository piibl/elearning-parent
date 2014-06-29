package fr.iut.csid.empower.elearning.web.link.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.web.controller.domain.user.StudentController;
import fr.iut.csid.empower.elearning.web.link.AbstractResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

/**
 * Représente les ressources associés à un étudiant existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class StudentResourceAssembler extends AbstractResourceAssembler<Student, Resource<Student>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public StudentResourceAssembler() {

	}

	@Override
	public Resource<Student> toResource(Student student) {
		Assert.notNull(student);
		// Adressage d'un étudiant
		Link selfLink = linkBuilderFactory.linkTo(StudentController.class).slash(student.getId()).withRel(Relation.SELF.getName());
		// Adressage de tous les étudiants
		Link studentsLink = linkBuilderFactory.linkTo(StudentController.class).withRel(Relation.STUDENTS.getName());
		// Adressage pour la suppression d'un étudiant
		Link deleteLink = linkBuilderFactory.linkTo(StudentController.class).slash(student.getId()).slash(PathFragment.DELETE.getPath())
				.withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'un étudiant
		Link editLink = linkBuilderFactory.linkTo(StudentController.class).slash(student.getId()).slash(PathFragment.EDIT.getPath())
				.withRel(Relation.EDIT.getName());
		// Adressage pour la visualisation des cours auxqueles est inscrit l'étudiant
		Link coursesLink = linkBuilderFactory.linkTo(StudentController.class).slash(student.getId()).slash(PathFragment.COURSES.getPath())
				.withRel(Relation.COURSES.getName());
		return new Resource<Student>(student, selfLink, studentsLink, deleteLink, editLink, coursesLink);
	}

}
