package fr.iut.csid.empower.elearning.web.link.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.web.controller.domain.course.CourseController;
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
				.slash(PathFragment.SESSIONS.getPath()).slash(courseSession.getId()).withRel(Relation.SELF.getName());
		// Adressage pour la suppression d'une session
		Link deleteLink = linkBuilderFactory.linkTo(CourseController.class).slash(courseSession.getOwnerCourse().getId())
				.slash(PathFragment.SESSIONS.getPath()).slash(courseSession.getId()).slash(PathFragment.DELETE.getPath())
				.withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'une session
		Link editLink = linkBuilderFactory.linkTo(CourseController.class).slash(courseSession.getOwnerCourse().getId())
				.slash(PathFragment.SESSIONS.getPath()).slash(courseSession.getId()).slash(PathFragment.EDIT.getPath())
				.withRel(Relation.EDIT.getName());
		// adressages des ressources d'une session
		Link manageResourcesFilesLink = linkBuilderFactory.linkTo(CourseController.class).slash(courseSession.getOwnerCourse().getId())
				.slash(PathFragment.SESSIONS.getPath()).slash(courseSession.getId()).slash(PathFragment.RESOURCES.getPath())
				.withRel(Relation.RESOURCES.getName());
		return new Resource<CourseSession>(courseSession, selfLink, deleteLink, editLink, manageResourcesFilesLink);
	}

}
