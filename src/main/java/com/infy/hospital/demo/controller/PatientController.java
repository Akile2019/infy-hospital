package com.infy.hospital.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.hospital.demo.dto.AppointmentDTO;
import com.infy.hospital.demo.exceptions.InvalidValueException;
import com.infy.hospital.demo.exceptions.NoAppointmentSlotAvailableException;
import com.infy.hospital.demo.request.AppointmentBookingRequest;
import com.infy.hospital.demo.service.AppointmentService;
import com.infy.hospital.demo.service.DoctorService;
import com.infy.hospital.demo.service.PatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@PostMapping("/appointment")
	public ResponseEntity<AppointmentDTO> makeAnAppointment(@RequestBody AppointmentBookingRequest request) {
		return new ResponseEntity(this.patientService.bookAnAppointment(request), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{pid}/appointment/{aid}")
	public ResponseEntity<String> cancelAppointment(@PathVariable("pid") String pId, @PathVariable("aid") Long aId) {
		System.out.println(pId);
		if (pId == null || aId == null) throw new InvalidValueException("required paramter is missing");
		
		if(this.patientService.cancelAppointment(pId, aId)) {
			return new ResponseEntity(aId + " cancelled successfully,", HttpStatus.OK);
		}
		return new ResponseEntity(aId + " is not cancelled,", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{pid}/appointments")
	public List<AppointmentDTO> getAllAppointments(@PathVariable("pid") String pId) {
		return this.patientService.getAllAppointments(pId);
	}
	
}
