package fr.iut.csid.empower.elearning.web.util.jodatime.thymeleaf.processor;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * The JodaTime expression object
 */
public class JodaTimeExpressionObject {

	private final Locale locale;

	/**
	 * constructeur par défaut
	 * 
	 * @param locale
	 */
	public JodaTimeExpressionObject(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Formate une date à la française, sisi.
	 * 
	 * @param dateTime
	 *            The datetime
	 * @return The formatted date
	 */
	public String customDateTime(DateTime dateTime) {
		return format(dateTime, DateTimeFormat.forPattern("dd/MM/yyyy HH:mm"));
	}

	/**
	 * Formatte une date au format spécifié
	 * 
	 * @param dateTime
	 *            The datetime
	 * @return The formatted date
	 */
	public String format(DateTime dateTime, String format) {
		return format(dateTime, DateTimeFormat.forPattern(format));
	}

	/**
	 * Formatte une date par le biais d'un formater
	 * 
	 * @param dateTime
	 *            The datetime
	 * @param formatter
	 *            The formatter
	 * @return The formatted date
	 */
	private String format(DateTime dateTime, DateTimeFormatter formatter) {
		return formatter.withLocale(locale).print(dateTime);
	}
}
