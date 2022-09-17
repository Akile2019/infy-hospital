package com.infy.hospital.demo.exceptions;

public class NoPatientFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5243321661412862935L;
	  
	public NoPatientFoundException(String message) {
		super(message);
	}

}
