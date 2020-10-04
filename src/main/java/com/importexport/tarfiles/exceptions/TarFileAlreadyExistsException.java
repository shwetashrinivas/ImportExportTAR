package com.importexport.tarfiles.exceptions;

public class TarFileAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public TarFileAlreadyExistsException(String message) {
		super(message);
	}

}
