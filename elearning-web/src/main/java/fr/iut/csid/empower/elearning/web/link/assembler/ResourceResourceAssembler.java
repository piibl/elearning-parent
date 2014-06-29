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
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilder;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

@Named
public class ResourceResourceAssembler
		extends
		AbstractResourceAssembler<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource, Resource<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource>>
		implements
		BatchResourceAssembler<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource, Resource<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public ResourceResourceAssembler() {

	}

	@Override
	public Resource<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource> toResource(
			fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource resource) {
		Assert.notNull(resource);
		// Extraction données
		CourseSession session = resource.getOwnerSession();
		// baseLink = courses/{courseId}/sessions/{sessionId}/resources/{resourceId}
		ControllerLinkBuilder baseLinkBuilder = linkBuilderFactory.linkTo(CourseController.class).slash(session.getOwnerCourse().getId())
				.slash(PathFragment.SESSIONS.getPath()).slash(session.getId()).slash(PathFragment.RESOURCES.getPath()).slash(resource.getId());
		// Adressage d'une resource
		Link selfLink = baseLinkBuilder.withRel(Relation.SELF.getName());
		// Adressage pour la suppression d'une resource
		Link deleteLink = baseLinkBuilder.slash(PathFragment.DELETE.getPath()).withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'une resource
		Link editLink = baseLinkBuilder.slash(PathFragment.EDIT.getPath()).withRel(Relation.EDIT.getName());
		return new Resource<fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource>(resource, selfLink, deleteLink, editLink);
	}
}
