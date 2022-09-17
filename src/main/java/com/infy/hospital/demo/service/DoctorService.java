package com.infy.hospital.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.hospital.demo.dao.DoctorDAO;
import com.infy.hospital.demo.dto.DoctorDTO;
import com.infy.hospital.demo.exceptions.InvalidDoctorException;
import com.infy.hospital.demo.model.Appointment;
import com.infy.hospital.demo.model.Doctor;
import com.infy.hospital.demo.request.AppointmentBookingRequest;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Autowired
	private AppointmentService appointmentService;
	
	private Logger  manager = LogManager.getFormatterLogger(DoctorService.class);
	
	public boolean hasDoctorExist(Long doctorId) {
		return doctorDAO.hasDoctorExist(doctorId);
		
	}

	public boolean cancelAppointment(Long drId, Long appointmentId) {
		if (!hasDoctorExist(drId))  {
			manager.debug("no dr present for given drId: " + drId);
			throw new InvalidDoctorException(drId + " is invalid");
		}
		manager.debug("cehcking whether appointment and dr is valid for drId:" + drId + " appiointmentId: " + appointmentId);
		Optional<Appointment> app = this.appointmentService.findAppointmentByDrIdAppointmentId(drId, appointmentId);
		manager.debug("valid appointment is found? " + app.isPresent());
		return app.isPresent();
	}

	public List<DoctorDTO> getAllDoctors() {
		return this.doctorDAO.getAllDoctors().stream()
						.map(dr -> new DoctorDTO(dr.getDoctorId(), dr.getName(), dr.getSpecialty()))
						.collect(Collectors.toList());
						
	}

	public Optional<Doctor> fetchDoctor(Long doctorId) {
		return this.doctorDAO.fetchDoctor(doctorId);
	}


}
