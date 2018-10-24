package br.com.systemmcr.sistemareserva.exceptions;

public class ApplicationException extends Exception{

	private static final long serialVersionUID = 8049436932107535711L;

	public ApplicationException( String message, Throwable cause ){
		super( message, cause );
	}

	public ApplicationException( String message ){
		super( message );
	}

}// fim da class ApplicationException
