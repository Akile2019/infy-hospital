package com.infy.hospital.demo.exceptions;

public class NoAppointmentSlotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5826975564354397994L;
	
	
	public NoAppointmentSlotAvailableException(String message) {
		super(message);
	}

}
