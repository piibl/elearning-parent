package fr.iut.csid.empower.elearning.web.service;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource;
import fr.iut.csid.empower.elearning.web.dto.impl.ResourceDTO;

public interface ResourceService extends OwnedEntityCrudService<Resource, Long, ResourceDTO> {

}
