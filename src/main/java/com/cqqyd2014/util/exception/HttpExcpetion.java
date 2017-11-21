package com.cqqyd2014.util.exception;

public class HttpExcpetion extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code;
	String url;
	public HttpExcpetion(String code, String url, String message) {
		super();
		this.code = code;
		this.url = url;
		this.message = message;
	}
	String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
