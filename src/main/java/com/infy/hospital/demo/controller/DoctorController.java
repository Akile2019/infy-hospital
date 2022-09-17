package com.infy.hospital.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.hospital.demo.dto.AppointmentDTO;
import com.infy.hospital.demo.dto.DoctorDTO;
import com.infy.hospital.demo.exceptions.AppointmentCancelledException;
import com.infy.hospital.demo.exceptions.InvalidDoctorException;
import com.infy.hospital.demo.exceptions.InvalidValueException;
import com.infy.hospital.demo.model.Doctor;
import com.infy.hospital.demo.service.AppointmentService;
import com.infy.hospital.demo.service.DoctorService;


@RestController
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	private Logger  manager = LogManager.getFormatterLogger(AppointmentService.class);
	
	@GetMapping("/{id}/appointments/status/{status}")
	public List<AppointmentDTO> getAllAppointmentsForGivenStatus(@PathVariable ("id") Long id, @PathVariable ("status") String status) {
		if (id == null || status == null) throw new InvalidValueException("required paramter(s) are missing");
		if (!doctorService.hasDoctorExist(id)) throw new InvalidDoctorException(id + " does not exist.");
	
		return appointmentService.findAllAppointmentsByDoctorIdAndStatus(id, status);
	}
	
	
	@GetMapping("/{id}/appointments")
	public List<AppointmentDTO> getAllAppointments(@PathVariable ("id") Long id) {
		 if (id == null) throw new InvalidValueException("required paramter is missing");
		 if (!doctorService.hasDoctorExist(id)) throw new InvalidDoctorException(id + " does not exist.");
		 return appointmentService.findAllAppointments(id);
	}
	
	@PutMapping("/{drid}/appointment/{aid}")
	public ResponseEntity<String> cancelAppointment(@PathVariable("drid") Long drId, @PathVariable("aid") Long aId) {
		manager.debug("request recieved to cancel appointment for dr: " + drId + " appointmentId: " + aId);
		if (drId == null || aId == null) throw new InvalidValueException("required paramter is missing");
		
		if(doctorService.cancelAppointment(drId, aId)) {
			manager.debug("appointment cancelled for dr: " + drId + " appointmentId: " + aId);
			return new ResponseEntity(aId + " cancelled successfully,", HttpStatus.OK);
		}
		manager.debug("appointment not cancelled for dr: " + drId + " appointmentId: " + aId);
		return new ResponseEntity(aId + " is not cancelled,", HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/")
	public List<DoctorDTO> getAllDoctors() {
		return doctorService.getAllDoctors();
	}
	
	
}