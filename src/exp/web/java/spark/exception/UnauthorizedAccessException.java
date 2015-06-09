package exp.web.java.spark.exception;

import exp.web.java.spark.dto.BaseDto;

/**
 * SparkDemo - exp.web.java.spark.auth
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 9, 2015
 */

public class UnauthorizedAccessException extends BaseException {

	private static final long serialVersionUID = 4829545436642371700L;

	private static final String messageKey = "error.unauthorized";
	
	private static final String errorCode  = "error.code.user.unauthorized";
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public BaseDto getResponse() {
		return getExceptionResponse(messageKey, errorCode);
	}
}