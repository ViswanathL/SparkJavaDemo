package exp.web.java.spark.dto;

import exp.web.java.spark.util.Util;

/**
 * SparkDemo - exp.web.java.spark.auth
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 7, 2015
 */

public abstract class BaseDto {

	private Boolean status;
	
	private String message;
	
	private Integer errorCode;

	public BaseDto() {
	}
	
	public BaseDto(String messageProperty) {
		this.status = true;
		if(messageProperty != null)
			message = new Util().readMessage(messageProperty);
	}
	
	public BaseDto(String messageProperty, Integer errorCode, boolean status) {
		this.status = status;
		this.errorCode = errorCode;
		if(messageProperty != null)
			message = new Util().readMessage(messageProperty);
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
}
