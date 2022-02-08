package com.orthochiqui.exception;

/**
 * Exceptions case
 * @author Jose
 *
 */
public class ContatoNotFoundException extends Exception {

	private static final long serialVersionUID = -4427526328244697036L;

	public ContatoNotFoundException(){
		super();
	}
	
	public ContatoNotFoundException(String msg){
		super(msg);
	}
	
	public ContatoNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
}
