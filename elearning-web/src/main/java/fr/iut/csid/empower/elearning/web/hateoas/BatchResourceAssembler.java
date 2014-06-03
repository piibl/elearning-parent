package fr.iut.csid.empower.elearning.web.hateoas;

import java.util.List;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;

public interface BatchResourceAssembler<T, D extends ResourceSupport> extends ResourceAssembler<T, D> {

	/**
	 * Construit les containers entités/liens par lot
	 * 
	 * @param entities
	 *            : liste d'entités à traiter
	 * @return : containers de ressources
	 */
	public List<D> toResource(List<T> entities);

}
