package fr.iut.csid.empower.elearning.core.service;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource;
import fr.iut.csid.empower.elearning.core.dto.impl.ResourceDTO;

public interface ResourceService extends OwnedEntityCrudService<Resource, Long, ResourceDTO> {

}
