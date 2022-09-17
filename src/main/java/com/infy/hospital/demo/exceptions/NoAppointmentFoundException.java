package com.infy.hospital.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NoAppointmentFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -16566536265822057L;

	public NoAppointmentFoundException(String message) {
		super(message);
	}
	
}
