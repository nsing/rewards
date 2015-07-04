package com.rewards.model;

/**
 * Response message enum.
 *
 */
public enum ResponseMessage {

	/** Customer is eligible message **/
	CUSTOMER_ELIGBILE_MSG("Customer is eligible"),

	/** Customer is not eligible message **/
	CUSTOMER_NOT_ELIGBILE_MSG("Customer is not eligible"),

	/** Account number is invalid message **/
	ACCOUNT_NUMBER_INVALID_MSG("Account number is invalid"),

	/** Service technical failure message **/
	SERVICE_TECHNICAL_FAILURE_MSG("service technical failure");

	/** Message **/
	private String message;

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            message
	 */
	ResponseMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns message.
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

}
