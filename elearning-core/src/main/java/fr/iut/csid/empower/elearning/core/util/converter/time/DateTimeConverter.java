package fr.iut.csid.empower.elearning.core.util.converter.time;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;

/**
 * Convertisseur objets DateTime (API JodaTime) / objets java.sql.Timestamp
 */
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<DateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(DateTime dateTime) {
		return dateTime == null ? null : new Timestamp(dateTime.getMillis());
	}

	@Override
	public DateTime convertToEntityAttribute(Timestamp timestamp) {
		return  timestamp == null ? null : new DateTime(timestamp);
	}

}
