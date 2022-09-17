package com.infy.hospital.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { AppointmentCancelledException.class })
	public ResponseEntity<String> appointmentCancelled(AppointmentCancelledException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value = { InvalidDoctorException.class })
	public ResponseEntity<String> invalidDoctpr(InvalidDoctorException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value = { NoAppointmentFoundException.class })
	public ResponseEntity<String> noAppointmentFound(NoAppointmentFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { NoAppointmentSlotAvailableException.class })
	public ResponseEntity<String> noAppointmentSlotAvailable(NoAppointmentSlotAvailableException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = { NoPatientFoundException.class })
	public ResponseEntity<String> noAppointmentSlotAvailable(NoPatientFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
