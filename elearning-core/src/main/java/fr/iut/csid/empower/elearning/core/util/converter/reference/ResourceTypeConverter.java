package fr.iut.csid.empower.elearning.core.util.converter.reference;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import fr.iut.csid.empower.elearning.core.reference.ResourceType;

/**
 * Converter pour stockage persistent : {@link ResourceType} <-> {@link String}
 * 
 * @author pblanchard
 */
@Converter(autoApply = true)
public class ResourceTypeConverter implements AttributeConverter<ResourceType, String> {

	@Override
	public String convertToDatabaseColumn(ResourceType attribute) {
		switch (attribute) {
		case EXAM:
			return ResourceType.EXAM.getId();
		case TEXT_MATERIAL:
			return ResourceType.TEXT_MATERIAL.getId();
		case VIDEO_MATERIAL:
			return ResourceType.VIDEO_MATERIAL.getId();
		case PICTURE_MATERIAL:
			return ResourceType.PICTURE_MATERIAL.getId();
		default:
			// TODO refactor exception...
			throw new IllegalArgumentException("Unknown resource type [" + attribute + "]");
		}
	}

	@Override
	public ResourceType convertToEntityAttribute(String dbData) {
		if (dbData.compareToIgnoreCase(ResourceType.EXAM.getId()) == 0)
			return ResourceType.EXAM;
		else if (dbData.compareToIgnoreCase(ResourceType.TEXT_MATERIAL.getId()) == 0)
			return ResourceType.TEXT_MATERIAL;
		else if (dbData.compareToIgnoreCase(ResourceType.PICTURE_MATERIAL.getId()) == 0)
			return ResourceType.PICTURE_MATERIAL;
		else if (dbData.compareToIgnoreCase(ResourceType.VIDEO_MATERIAL.getId()) == 0)
			return ResourceType.VIDEO_MATERIAL;
		else
			// TODO refactor exception
			throw new IllegalArgumentException("Unknown resource type [" + dbData + "]");

	}

}