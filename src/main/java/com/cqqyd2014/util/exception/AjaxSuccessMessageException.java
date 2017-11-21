package com.cqqyd2014.util.exception;

public class AjaxSuccessMessageException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AjaxSuccessMessageException(String messageString) {
		super();
		this.messageString = messageString;
	}

	String messageString;

	public String getMessageString() {
		return messageString;
	}

	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}

}
