package com.infy.hospital.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidDoctorException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5278341212502470322L;

	public InvalidDoctorException(String message) {
		super(message);
	}

}
