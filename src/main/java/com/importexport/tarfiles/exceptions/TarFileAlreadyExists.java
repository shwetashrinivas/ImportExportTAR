package com.importexport.tarfiles.exceptions;

public class TarFileAlreadyExists extends Exception{

	private static final long serialVersionUID = 1L;

	public TarFileAlreadyExists(String message) {
		super(message);
	}

}
