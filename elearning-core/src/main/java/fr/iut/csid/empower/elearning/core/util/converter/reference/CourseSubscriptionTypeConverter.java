package fr.iut.csid.empower.elearning.core.util.converter.reference;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionType;

/**
 * Converter pour stockage persistent : {@link CourseSubscriptionType} <-> {@link Integer} 
 * @author pblanchard
 *
 */
@Converter(autoApply = true)
public class CourseSubscriptionTypeConverter implements AttributeConverter<CourseSubscriptionType, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(CourseSubscriptionType attribute) {
        switch (attribute) {
            case CONSULTATIVE:
                return CourseSubscriptionType.CONSULTATIVE.getId();
            case PARTICIPATIVE:
                return CourseSubscriptionType.PARTICIPATIVE.getId();
            default:
            	// TODO refactor exception...
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }
 
    @Override
    public CourseSubscriptionType convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 1:
                return CourseSubscriptionType.CONSULTATIVE;
            case 2:
                return CourseSubscriptionType.PARTICIPATIVE;
            default:
            	// TODO refactor exception
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
    
}