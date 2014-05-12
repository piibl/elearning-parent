package fr.iut.csid.empower.elearning.core.router;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iut.csid.empower.elearning.core.job.init.InitializationJob;
import fr.iut.csid.empower.elearning.core.job.reader.impl.DataReader;

@Named
public class Router {

	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(Router.class);

	/**
	 * Job d'initialisation des données
	 */
	@Inject
	private InitializationJob initializationService;

	/**
	 * Job d'accès aux données (consultation)
	 */
	@Inject
	private DataReader dataReader;

	public void launch() {
		/**
		 * 
		 * Insertion des données
		 * 
		 * 
		 */
		logger.info("App data initialization...");
		initializationService.process();
		logger.info("End of data initialization...");

		/**
		 * Affichage données, exercice d'accès aux données
		 */
		logger.info("Data reader start...");
		dataReader.process();
		logger.info("Data reader stop...");

	}
}
