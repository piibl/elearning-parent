package fr.iut.csid.empower.elearning.web.controller.user.validator;

import javax.inject.Named;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.iut.csid.empower.elearning.core.domain.user.Student;

/**
 * Validateur bidon pour démonstration d'implémentation
 */
@Named
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Student shop = (Student) target;
		// errors.rejectValue("firstName", "Can't be empty !");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty");

	}

}
