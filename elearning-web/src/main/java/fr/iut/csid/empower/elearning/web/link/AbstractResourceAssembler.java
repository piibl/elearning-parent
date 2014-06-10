package fr.iut.csid.empower.elearning.web.link;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.util.Assert;

public abstract class AbstractResourceAssembler<T, D extends ResourceSupport> implements BatchResourceAssembler<T, D> {

	@Override
	public List<D> toResource(List<T> entities) {
		Assert.notNull(entities);
		List<D> resourceList = new ArrayList<D>();
		for (T entity : entities) {
			resourceList.add(toResource(entity));
		}
		return resourceList;
	}

}
