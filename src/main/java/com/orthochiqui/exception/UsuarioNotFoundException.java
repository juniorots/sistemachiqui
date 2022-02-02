package com.orthochiqui.exception;

/**
 * Exceptions case
 * @author Jose
 *
 */
public class UsuarioNotFoundException extends Exception {

	private static final long serialVersionUID = 5922878980338883642L;

	public UsuarioNotFoundException(){
		super();
	}
	
	public UsuarioNotFoundException(String msg){
		super(msg);
	}
	
	public UsuarioNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
}
