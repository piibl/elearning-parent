package fr.iut.csid.empower.elearning.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.iut.csid.empower.elearning.core.router.Router;

/**
 * Point d'entrée de l'application.
 * 
 * @author Pierre_pers
 */
public class StartRouter {
	/**
	 * Contexte de l'application.
	 */
	private static ClassPathXmlApplicationContext applicationContext;

	/**
	 * Démarre l'application.
	 * 
	 * @param args
	 *            : Aucun argument attendu.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Logger logger = LoggerFactory.getLogger(StartRouter.class);

		// Tracer le démarrage
		logger.info(">>> App startup ...");

		// ---------------------------------------
		// Chargement du contexte Spring
		// ---------------------------------------

		// Tracer le chargement du contexte Spring
		logger.info(">>> Starting Spring context ...");

		try {

			// Chargement du container et instanciation du contexte
			applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/core-context.xml");

		} catch (Throwable e) {

			// Tracer l'erreur dans la log
			logger.error("Unexpected exception occurred during spring context configuration [" + e.getMessage() + "]");
			e.printStackTrace();
			// Arrêt de la jvm
			System.exit(0);
		}

		// Tracer la fin du chargement du contexte Spring
		logger.info(">>> Spring context has been created.");

		// ---------------------------------------
		// Lancement du router
		// ---------------------------------------

		Router router = applicationContext.getBean(Router.class);
		router.launch();

		// ---------------------------------------
		// Fin de traitement, arrêt de l'application
		// ---------------------------------------
		logger.info(">>> App shut down...");
	}
}
