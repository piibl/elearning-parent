package fr.iut.csid.empower.elearning.core.util.converter.reference;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import fr.iut.csid.empower.elearning.core.reference.UserRole;

/**
 * Converter pour stockage persistent : {@link UserRole} <-> {@link String}
 */
@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

	@Override
	public String convertToDatabaseColumn(UserRole attribute) {
		switch (attribute) {
		case ADMINISTRATOR:
			return UserRole.ADMINISTRATOR.getRole();
		case TEACHER:
			return UserRole.TEACHER.getRole();
		case STUDENT:
			return UserRole.STUDENT.getRole();
		default:
			// TODO refactor exception...
			throw new IllegalArgumentException("Unknown user role [" + attribute + "]");
		}
	}

	@Override
	public UserRole convertToEntityAttribute(String dbData) {
		if (dbData.compareToIgnoreCase(UserRole.ADMINISTRATOR.getRole()) == 0)
			return UserRole.ADMINISTRATOR;
		else if (dbData.compareToIgnoreCase(UserRole.TEACHER.getRole()) == 0)
			return UserRole.TEACHER;
		else if (dbData.compareToIgnoreCase(UserRole.STUDENT.getRole()) == 0)
			return UserRole.STUDENT;
		else
			// TODO refactor exception
			throw new IllegalArgumentException("Unknown user role [" + dbData + "]");

	}

}