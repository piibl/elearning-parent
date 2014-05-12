package fr.iut.csid.empower.elearning.core.util.log;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Adaptateur SL4J pour le session logger d'EclipseLink
 * 
 * @author pblanchard
 */
public class Slf4JELSessionLogger extends AbstractSessionLog {

	/**
	 * Package de logging d'EL => namespace
	 */
	public static final String ECLIPSELINK_NAMESPACE = "org.eclipse.persistence.logging";
	/**
	 * Catégorie de traces par défaut = "default"
	 */
	public static final String DEFAULT_CATEGORY = "default";
	private static final String DEFAULT_ECLIPSELINK_NAMESPACE = ECLIPSELINK_NAMESPACE + "." + DEFAULT_CATEGORY;

	/**
	 * Niveaux de traces possibles
	 */
	private Map<Integer, LogLevel> mapLevels;

	/**
	 * Catégories de traces possibles
	 */
	private Map<String, Logger> categoryLoggers = new HashMap<String, Logger>();

	public Slf4JELSessionLogger() {
		super();
		// INIT
		createCategoryLoggers();
		initMapLevels();
	}

	/**
	 * comportement par défaut = debug
	 */
	@Override
	public void log(SessionLogEntry entry) {
		if (!shouldLog(entry.getLevel(), entry.getNameSpace())) {
			return;
		}

		Logger logger = getLogger(entry.getNameSpace());
		LogLevel logLevel = getLogLevel(entry.getLevel());

		StringBuilder message = new StringBuilder();

		message.append(getSupplementDetailString(entry));
		message.append(formatMessage(entry));

		switch (logLevel) {
		case TRACE:
			logger.trace(message.toString());
			break;
		case DEBUG:
			logger.debug(message.toString());
			break;
		case INFO:
			logger.info(message.toString());
			break;
		case WARN:
			logger.warn(message.toString());
			break;
		case ERROR:
			logger.error(message.toString());
			break;
		default:
			logger.debug(message.toString());
			break;
		}
	}

	/**
	 * comportement par défaut = debug
	 */
	@Override
	public boolean shouldLog(int level, String category) {
		Logger logger = getLogger(category);
		boolean resp = false;

		LogLevel logLevel = getLogLevel(level);

		switch (logLevel) {
		case TRACE:
			resp = logger.isTraceEnabled();
			break;
		case DEBUG:
			resp = logger.isDebugEnabled();
			break;
		case INFO:
			resp = logger.isInfoEnabled();
			break;
		case WARN:
			resp = logger.isWarnEnabled();
			break;
		case ERROR:
			resp = logger.isErrorEnabled();
			break;
		default:
			resp = logger.isDebugEnabled();
			break;
		}

		return resp;
	}

	@Override
	public boolean shouldLog(int level) {
		return shouldLog(level, "default");
	}

	@Override
	public boolean shouldDisplayData() {
		if (this.shouldDisplayData != null) {
			return shouldDisplayData.booleanValue();
		} else {
			return false;
		}
	}

	/**
	 * Initialise les loggers pour les différentes catégories
	 */
	private void createCategoryLoggers() {
		for (String category : SessionLog.loggerCatagories) {
			addLogger(category, ECLIPSELINK_NAMESPACE + "." + category);
		}
		// Logger par défaut
		addLogger(DEFAULT_CATEGORY, DEFAULT_ECLIPSELINK_NAMESPACE);
	}

	/**
	 * Ajoute un logger dans la map des catégories
	 */
	private void addLogger(String loggerCategory, String loggerNameSpace) {
		categoryLoggers.put(loggerCategory, LoggerFactory.getLogger(loggerNameSpace));
	}

	/**
	 * Retourne le logger correspondant à une catégorie donnée
	 */
	private Logger getLogger(String category) {

		if (!StringUtils.hasText(category) || !this.categoryLoggers.containsKey(category)) {
			category = DEFAULT_CATEGORY;
		}

		return categoryLoggers.get(category);

	}

	/**
	 * Retourne la correspondance d'un niveau de trace EL en niveau slf4j
	 */
	private LogLevel getLogLevel(Integer level) {
		LogLevel logLevel = mapLevels.get(level);

		if (logLevel == null)
			logLevel = LogLevel.OFF;

		return logLevel;
	}

	/**
	 * Niveaux de traces Slf4j
	 */
	enum LogLevel {
		TRACE, DEBUG, INFO, WARN, ERROR, OFF
	}

	/**
	 * Initialise la map des correspondances entre les niveaux de traces d'EL et ceux de Slf4j
	 */
	private void initMapLevels() {

		mapLevels = new HashMap<Integer, LogLevel>();
		mapLevels.put(SessionLog.ALL, LogLevel.TRACE);
		mapLevels.put(SessionLog.FINEST, LogLevel.TRACE);
		mapLevels.put(SessionLog.FINER, LogLevel.TRACE);
		mapLevels.put(SessionLog.FINE, LogLevel.DEBUG);
		mapLevels.put(SessionLog.CONFIG, LogLevel.INFO);
		mapLevels.put(SessionLog.INFO, LogLevel.INFO);
		mapLevels.put(SessionLog.WARNING, LogLevel.WARN);
		mapLevels.put(SessionLog.SEVERE, LogLevel.ERROR);
	}

}
