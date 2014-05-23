package fr.iut.csid.empower.elearning.core.util.converter.reference;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionStatus;

/**
 * Converter pour stockage persistent : {@link CourseSubscriptionStatus} <-> {@link Integer} 
 * @author pblanchard
 *
 */
@Converter(autoApply = true)
public class CourseSubscriptionStatusConverter implements AttributeConverter<CourseSubscriptionStatus, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(CourseSubscriptionStatus attribute) {
        switch (attribute) {
            case IN_PROGRESS:
                return CourseSubscriptionStatus.IN_PROGRESS.getId();
            case VALIDATED:
                return CourseSubscriptionStatus.VALIDATED.getId();
            case CANCELLED:
            	return CourseSubscriptionStatus.CANCELLED.getId();
            default:
            	// TODO refactor exception...
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }
 
    @Override
    public CourseSubscriptionStatus convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 1:
                return CourseSubscriptionStatus.IN_PROGRESS;
            case 2:
                return CourseSubscriptionStatus.VALIDATED;
            case 3:
                return CourseSubscriptionStatus.CANCELLED;
            default:
            	// TODO refactor exception
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}