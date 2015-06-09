package exp.web.java.spark.servlet;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * SparkDemo - exp.web.java.spark.entity
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 23, 2015
 */

public class AppServletListener implements ServletContextListener {

	private Logger logger = Logger.getLogger(AppServletListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			logger.info("Spark Application starting up.");
			logger.info("Initializing Memcache...");
			logger.info("Memcache initialialized.\n");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Shutting down Spark Application...\nContext destroyed.");
	}

}
