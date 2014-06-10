package fr.iut.csid.empower.elearning.web.link.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.web.controller.entity.course.CourseController;
import fr.iut.csid.empower.elearning.web.link.AbstractResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

@Named
public class CourseSessionResourceAssembler extends AbstractResourceAssembler<CourseSession, Resource<CourseSession>> implements
		BatchResourceAssembler<CourseSession, Resource<CourseSession>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public CourseSessionResourceAssembler() {

	}

	@Override
	public Resource<CourseSession> toResource(CourseSession courseSession) {
		Assert.notNull(courseSession);
		// Adressage d'une session
		Link selfLink = linkBuilderFactory.linkTo(CourseController.class).slash(courseSession.getOwnerCourse().getId())
				.slash(Relation.SESSIONS.getName()).slash(courseSession.getId()).withRel(Relation.SELF.getName());
		// Adressage pour la suppression d'une session
		Link deleteLink = linkBuilderFactory.linkTo(CourseController.class).slash(courseSession.getOwnerCourse().getId())
				.slash(Relation.SESSIONS.getName()).slash(courseSession.getId()).slash(PathFragment.DELETE.getPath())
				.withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'une session
		Link editLink = linkBuilderFactory.linkTo(CourseController.class).slash(courseSession.getOwnerCourse().getId())
				.slash(Relation.SESSIONS.getName()).slash(courseSession.getId()).slash(PathFragment.EDIT.getPath()).withRel(Relation.EDIT.getName());
		// Adressage pour accéder aux sessions d'un cours
		// Link sessionsLink = linkBuilderFactory.linkTo(CourseController.class).slash(courseSession.getId()).slash(PathFragment.SESSIONS.getPath())
		// .withRel(Relation.SESSIONS.getName());
		// TODO chapitres
		// TODO notes
		// TODO étudiants / profs rattachés
		return new Resource<CourseSession>(courseSession, selfLink, deleteLink, editLink);
	}

}
