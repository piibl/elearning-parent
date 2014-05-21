package fr.iut.csid.empower.elearning.core.util.time;

import java.sql.Timestamp;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.DateTime;

/**
 * Convertisseur objets DateTime (API JodaTime) / objets java.sql.Timestamp
 */
public class DateTimeConverter implements Converter {

	private static final long serialVersionUID = 1L;

	@Override
	public Object convertDataValueToObjectValue(Object dataValue, Session session) {
		return dataValue == null ? null : new DateTime((Timestamp) dataValue);
	}

	@Override
	public Object convertObjectValueToDataValue(Object objectValue, Session session) {
		return objectValue == null ? null : new Timestamp(((DateTime) objectValue).getMillis());
	}

	@Override
	public void initialize(DatabaseMapping mapping, Session session) {
	}

	@Override
	public boolean isMutable() {
		return false;
	}

}
