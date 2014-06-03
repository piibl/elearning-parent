package fr.iut.csid.empower.elearning.web.hateoas;

import javax.inject.Named;

import org.springframework.hateoas.LinkBuilderFactory;

@Named
public class ControllerLinkBuilderFactory implements LinkBuilderFactory<ControllerLinkBuilder> {

	@Override
	public ControllerLinkBuilder linkTo(Class<?> target) {
		return ControllerLinkBuilder.linkTo(target);
	}

	@Override
	public ControllerLinkBuilder linkTo(Class<?> target, Object... parameters) {
		return ControllerLinkBuilder.linkTo(target, parameters);
	}

}
