package com.orthochiqui.exception;

/**
 * Exceptions case
 * @author Jose
 */
public class AgendaNotFoundException extends Exception {

	private static final long serialVersionUID = -7698359237732702796L;

	public AgendaNotFoundException(){
		super();
	}
	
	public AgendaNotFoundException(String msg){
		super(msg);
	}
	
	public AgendaNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
}
