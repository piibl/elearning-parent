package fr.iut.csid.empower.elearning.web.util.jodatime.thymeleaf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;
import org.thymeleaf.processor.IProcessor;

import fr.iut.csid.empower.elearning.web.util.jodatime.thymeleaf.processor.JodaTimeExpressionObject;
import fr.iut.csid.empower.elearning.web.util.jodatime.thymeleaf.processor.JodaTimeFormatProcessor;

/**
 * Un petit dialecte jodatime pour thymeleaf...
 */
public class JodaTimeDialect extends AbstractDialect implements IExpressionEnhancingDialect {

	public static final String JODA = "joda";

	@Override
	public String getPrefix() {
		return JODA;
	}

	@Override
	public Set<IProcessor> getProcessors() {
		Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new JodaTimeFormatProcessor("fullDate", DateTimeFormat.fullDate()));
		processors.add(new JodaTimeFormatProcessor("fullDateTime", DateTimeFormat.fullDateTime()));
		processors.add(new JodaTimeFormatProcessor("fullTime", DateTimeFormat.fullTime()));
		processors.add(new JodaTimeFormatProcessor("longDate", DateTimeFormat.longDate()));
		processors.add(new JodaTimeFormatProcessor("longDateTime", DateTimeFormat.longDateTime()));
		processors.add(new JodaTimeFormatProcessor("longTime", DateTimeFormat.longTime()));
		processors.add(new JodaTimeFormatProcessor("mediumDate", DateTimeFormat.mediumDate()));
		processors.add(new JodaTimeFormatProcessor("mediumDateTime", DateTimeFormat.mediumDateTime()));
		processors.add(new JodaTimeFormatProcessor("mediumTime", DateTimeFormat.mediumTime()));
		processors.add(new JodaTimeFormatProcessor("shortDate", DateTimeFormat.shortDate()));
		processors.add(new JodaTimeFormatProcessor("shortDateTime", DateTimeFormat.shortDateTime()));
		processors.add(new JodaTimeFormatProcessor("shortTime", DateTimeFormat.shortTime()));
		processors.add(new JodaTimeFormatProcessor("isoDateTime", ISODateTimeFormat.dateTime()));
		return processors;
	}

	@Override
	public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
		Map<String, Object> expressionObjects = new HashMap<String, Object>();
		expressionObjects.put(JODA, new JodaTimeExpressionObject(processingContext.getContext().getLocale()));
		return expressionObjects;
	}
}
