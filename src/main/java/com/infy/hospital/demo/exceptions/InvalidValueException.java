package com.infy.hospital.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidValueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4175658756256509768L;
	
	public InvalidValueException(String message) {
		super(message);
	}
	

}
