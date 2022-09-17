package com.infy.hospital.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AppointmentCancelledException extends RuntimeException {

	
	private static final long serialVersionUID = 7249319354244594385L;

	public AppointmentCancelledException(String message) {
		super(message);
	}

}
