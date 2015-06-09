package exp.web.java.spark.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <p>
 * SparkDemo - exp.web.java.spark.entity
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 7, 2015
 * <p>
 *
 * This class is used as utility class.<br>
 * All the util methods will be listed here.
 */

public class Util {

	/** Logger instance **/
	private static Logger logger = Logger.getLogger(Util.class);

	private static final String messagesBundle = "messages.properties";

	private static final String configBundle = "config.properties";
	
	/**
	 * Used to read the properties file
	 * @param propFileName
	 * @return Property file if exists, else null
	 */
	public Properties readPropertiesFile(String propFileName) {
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			try {
				properties.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("Property file '" + propFileName + "' not found in the classpath");
		}
		return properties;
	}

	/**
	 * This method is used to read a message
	 * @param messageKey
	 * @return Value of given property
	 */
	public String readMessage(String messageKey) {
		String value = null;
		Properties properties = readPropertiesFile(messagesBundle);
		if(properties != null)
			value = properties.getProperty(messageKey);
		return value;
	}

	/**
	 * This method is used to read a config property
	 * @param key
	 * @return Value of given property
	 */
	public String readConfigProperty(String key) {
		String value = null;
		Properties properties = readPropertiesFile(configBundle);
		if(properties != null)
			value = properties.getProperty(key);
		return value;
	}

}
