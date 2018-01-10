package com.cqqyd2014.util.exception;

public class AjaxSuccessMessageException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AjaxSuccessMessageException(String message,Object o1) {
		super(message);
		
		this.o1=o1;
	}
	

	

	

	public AjaxSuccessMessageException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}






	Object o1;

	public Object getO1() {
		return o1;
	}

	public void setO1(Object o1) {
		this.o1 = o1;
	}
}
