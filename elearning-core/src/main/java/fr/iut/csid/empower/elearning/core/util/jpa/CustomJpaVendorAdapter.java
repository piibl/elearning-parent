package fr.iut.csid.empower.elearning.core.util.jpa;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

/**
 * Adaptateur JPA
 * 
 * @author pblanchard
 */
public class CustomJpaVendorAdapter extends EclipseLinkJpaVendorAdapter {

	/**
	 * VALEURS DEFAUTS --------------- <br/>
	 * Ces valeurs sont attribuées que si les paramètres ne sont pas spécifiés dans la configuration de la datasource, voir fichier de configuration
	 * Spring<br/>
	 * TODO REFACTOR erreur si pas de config définie
	 */

	private String ddl_generation_mode = "database";
	private String ddl_generation = "create-tables";
	private String database_type = "Oracle";
	private String logging_level = "config";
	private String logging_logger = "org.eclipse.persistence.logging.SessionLog";

	@Override
	public Map<String, Object> getJpaPropertyMap() {

		Map<String, Object> jpaProperties = new HashMap<String, Object>();

		// Attribution DB
		setDatabase(Database.valueOf(database_type));

		// Surcharge si définis
		if (getDatabasePlatform() != null) {
			jpaProperties.put(PersistenceUnitProperties.TARGET_DATABASE, getDatabasePlatform());
		} else if (getDatabase() != null) {
			String targetDatabase = determineTargetDatabaseName(getDatabase());
			if (targetDatabase != null) {
				jpaProperties.put(PersistenceUnitProperties.TARGET_DATABASE, targetDatabase);
			}
		}
		// options DDL et génération schémas
		if (isGenerateDdl()) {
			jpaProperties.put(PersistenceUnitProperties.DDL_GENERATION, ddl_generation);
			jpaProperties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, ddl_generation_mode);
		}
		// Log
		// TODO adaptateur sl4j
		jpaProperties.put(PersistenceUnitProperties.LOGGING_LEVEL, logging_level);
		jpaProperties.put(PersistenceUnitProperties.LOGGING_LOGGER, logging_logger);

		return jpaProperties;
	}

	public String getDatabase_type() {
		return database_type;
	}

	public void setDatabase_type(String databaseType) {
		database_type = databaseType;
	}

	public String getDdl_generation_mode() {
		return ddl_generation_mode;
	}

	public void setDdl_generation_mode(String ddlGenerationMode) {
		ddl_generation_mode = ddlGenerationMode;
	}

	public String getDdl_generation() {
		return ddl_generation;
	}

	public void setDdl_generation(String ddl_generation) {
		this.ddl_generation = ddl_generation;
	}

	public String getLogging_level() {
		return logging_level;
	}

	public void setLogging_level(String logging_level) {
		this.logging_level = logging_level;
	}

	public String getLogging_logger() {
		return logging_logger;
	}

	public void setLogging_logger(String logging_logger) {
		this.logging_logger = logging_logger;
	}
}
