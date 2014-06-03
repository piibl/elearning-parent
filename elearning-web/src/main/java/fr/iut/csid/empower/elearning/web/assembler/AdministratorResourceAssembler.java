package fr.iut.csid.empower.elearning.web.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.web.controller.user.AdministratorController;
import fr.iut.csid.empower.elearning.web.hateoas.AbstractResourceAssembler;
import fr.iut.csid.empower.elearning.web.hateoas.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;

/**
 * Représente les ressources associés à un administrateur existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class AdministratorResourceAssembler extends AbstractResourceAssembler<Administrator, Resource<Administrator>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public AdministratorResourceAssembler() {

	}

	@Override
	public Resource<Administrator> toResource(Administrator administrator) {
		Assert.notNull(administrator);
		// Adressage d'un administrateur
		Link selfLink = linkBuilderFactory.linkTo(AdministratorController.class).slash(administrator.getId()).withRel(Relation.SELF.getName());
		// Adressage de tous les administrateurs
		Link studentsLink = linkBuilderFactory.linkTo(AdministratorController.class).withRel(Relation.ADMINISTRATORS.getName());
		// Adressage pour la suppression d'un administrateur
		Link deleteLink = linkBuilderFactory.linkTo(AdministratorController.class).slash(administrator.getId()).slash(PathFragment.DELETE.getName())
				.withRel(Relation.DELETE.getName());
		// Adressage pour la modification d'un administrateur
		Link editLink = linkBuilderFactory.linkTo(AdministratorController.class).slash(administrator.getId()).slash(PathFragment.EDIT.getName())
				.withRel(Relation.EDIT.getName());
		return new Resource<Administrator>(administrator, selfLink, studentsLink, deleteLink, editLink);
	}

}
