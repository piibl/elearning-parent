package fr.iut.csid.empower.elearning.web.service;

import fr.iut.csid.empower.elearning.core.domain.course.session.resource.SessionResource;
import fr.iut.csid.empower.elearning.web.dto.impl.ResourceDTO;

public interface ResourceService extends OwnedEntityManagerService<SessionResource, Long, ResourceDTO> {

}
