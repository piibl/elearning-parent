package fr.iut.csid.empower.elearning.web.link.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.web.controller.domain.notification.NotificationController;
import fr.iut.csid.empower.elearning.web.link.AbstractResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

/**
 * Représente les ressources associés à un cours existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class NotificationResourceAssembler extends AbstractResourceAssembler<Notification, Resource<Notification>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public NotificationResourceAssembler() {

	}

	@Override
	public Resource<Notification> toResource(Notification notification) {
		Assert.notNull(notification);
		// Adressage pour la suppression d'un cours
		Link deleteLink = linkBuilderFactory.linkTo(NotificationController.class).slash(PathFragment.NOTIFICATIONS.getPath())
				.slash(notification.getId()).slash(PathFragment.DELETE.getPath()).withRel(Relation.DELETE.getName());
		return new Resource<Notification>(notification, deleteLink);
	}

}
