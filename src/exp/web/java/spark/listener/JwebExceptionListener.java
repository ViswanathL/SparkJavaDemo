package exp.web.java.spark.listener;

import exp.web.java.spark.dto.BaseDto;

/**
 * Amrit - exp.jweb.amrit.exception
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 8, 2015
 */

public interface JwebExceptionListener {

	/**
	 * This method is used to get the response for an exception.
	 * @param messageKey
	 * @param errorCode
	 * @return BaseDto
	 */
	public BaseDto getExceptionResponse(String messageKey, String errorCode);
	
}
