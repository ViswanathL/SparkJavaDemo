package exp.web.java.spark.exception;

import org.apache.log4j.Logger;

import exp.web.java.spark.dto.BaseDto;
import exp.web.java.spark.dto.SimpleDto;
import exp.web.java.spark.listener.JwebExceptionListener;
import exp.web.java.spark.util.Util;

/**
 * SparkDemo - exp.web.java.spark.auth
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 8, 2015
 */

public abstract class BaseException extends Exception implements JwebExceptionListener {

	private static final long serialVersionUID = 5513557852007098039L;

	private Util util;

	public BaseException() {
		util = new Util();
	}

	/** Logger instance **/
	private Logger logger = Logger.getLogger(BaseException.class);

	@Override
	public BaseDto getExceptionResponse(String messageKey, String errorCode) {
		BaseDto baseDto = new SimpleDto(messageKey);
		baseDto.setStatus(false);
		try {
			baseDto.setErrorCode(Integer.parseInt(util.readMessage(errorCode)));
		} catch(Exception e) {
			logger.info("Integer conversion failed: " + errorCode);
			logger.debug(e);
		}
		return baseDto;
	}

	public abstract BaseDto getResponse();

}
