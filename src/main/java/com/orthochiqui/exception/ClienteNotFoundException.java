package com.orthochiqui.exception;

/**
 * Exceptions case
 * @author Jose
 *
 */
public class ClienteNotFoundException extends Exception {

	private static final long serialVersionUID = 1254459184041361512L;

	public ClienteNotFoundException(){
		super();
	}
	
	public ClienteNotFoundException(String msg){
		super(msg);
	}
	
	public ClienteNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
}
