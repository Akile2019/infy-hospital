package com.infy.hospital.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.infy.hospital.demo.dao.PatinentDAO;
import com.infy.hospital.demo.dto.AppointmentDTO;
import com.infy.hospital.demo.exceptions.InvalidDoctorException;
import com.infy.hospital.demo.exceptions.NoAppointmentFoundException;
import com.infy.hospital.demo.exceptions.NoAppointmentSlotAvailableException;
import com.infy.hospital.demo.exceptions.NoPatientFoundException;
import com.infy.hospital.demo.model.Appointment;
import com.infy.hospital.demo.model.Doctor;
import com.infy.hospital.demo.model.Patient;
import com.infy.hospital.demo.projection.AppointmentProjection;
import com.infy.hospital.demo.projection.PatientProjection;
import com.infy.hospital.demo.request.AppointmentBookingRequest;

@Service
public class PatientService {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PatinentDAO patientDAO;
	
	public AppointmentDTO bookAnAppointment(AppointmentBookingRequest request) {
	
     	Optional<Doctor> doctor = doctorService.fetchDoctor(request.getDoctorId());
     	if (!doctor.isPresent()) throw new InvalidDoctorException(request.getDoctorId() + " doesnt exist.");
     	
		Optional<Patient> patient = patientDAO.fetchPatient(request.getPatientId());
		if (!patient.isPresent()) {
			throw new NoPatientFoundException(request.getPatientId() + " is invalid");
		}
		
		Optional<AppointmentDTO> appointment = appointmentService.bookSlotIfAvailable(doctor.get(), patient.get(), request.getAppointmentTime(),
				request.getSymptoms());
		if (!appointment.isPresent()) {
			throw new NoAppointmentSlotAvailableException("Appointment slot is not available, please book another");
		}
		
		return appointment.get();
		
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public boolean cancelAppointment(String pId, Long aId) {
		Optional<AppointmentProjection> app = this.appointmentService.cancelAppointmentByPatient(pId, aId);
		if (!app.isPresent()) throw new NoAppointmentFoundException("Appointment id: " + aId + " doesn't exist.");
		return true;
	}

	public List<AppointmentDTO> getAllAppointments(String pId) {
		Optional<Patient> patient = this.patientDAO.fetchPatient(pId);
		if (patient.isPresent()) {
			return patient.get().getAppointments().stream()
					.map(app -> new AppointmentDTO(app.getAppointmentId(), app.getAppointmentStatus().name(), 
							app.getDoctor().getDoctorId(), pId, app.getSymptoms(), app.getDate(), app.getDoctor().getName()))
					.collect(Collectors.toList());
		}
		return new ArrayList<>();
		
	}

}
