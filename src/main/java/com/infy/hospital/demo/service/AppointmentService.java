package com.infy.hospital.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.hospital.demo.dao.AppointmentDAO;
import com.infy.hospital.demo.dto.AppointmentDTO;
import com.infy.hospital.demo.exceptions.AppointmentCancelledException;
import com.infy.hospital.demo.exceptions.InvalidDoctorException;
import com.infy.hospital.demo.exceptions.NoAppointmentFoundException;
import com.infy.hospital.demo.model.Appointment;
import com.infy.hospital.demo.model.AppointmentStatus;
import com.infy.hospital.demo.model.Doctor;
import com.infy.hospital.demo.model.Patient;
import com.infy.hospital.demo.projection.AppointmentProjection;
import com.infy.hospital.demo.repository.AppointmentRepository;
import com.infy.hospital.demo.repository.DoctorRepository;
import com.infy.hospital.demo.request.AppointmentBookingRequest;

@Service
public class AppointmentService {
	
	private Logger  manager = LogManager.getFormatterLogger(AppointmentService.class);
	
	@Autowired
	private AppointmentDAO appointmentDAO;

	public List<AppointmentDTO> findAllAppointments(long id) {
		manager.debug("finding all appointments for doctor id:" + id);
	//	if (!this.doctorService.hasDoctorExist(id)) throw new InvalidDoctorException(id + " is invalid");
		List<AppointmentProjection> data = this.appointmentDAO.findAllAppointmentsByDoctorId(id);
		manager.debug("for doctor: " + id + ", " + data.size() + " projection found");
		return data.stream().map(d -> 
						new AppointmentDTO(d.getAppointmentId(), d.getAppointmentStatus(),
											d.getDoctorId(), d.getPatientId(), d.getSymptoms(), d.getDate(), null))
		.collect(Collectors.toList());
	}

	public List<AppointmentDTO> findAllAppointmentsByDoctorIdAndStatus(Long id, String status) {
		
	//	if(!this.doctorService.hasDoctorExist(id)) throw new InvalidDoctorException(id + " is invalid");
		List<AppointmentProjection> data = this.appointmentDAO.findAllAppointmentsByDoctorIdAndStatus(id, status);
		return data.stream().map(d -> 
						new AppointmentDTO(d.getAppointmentId(), d.getAppointmentStatus(),
											d.getDoctorId(), d.getPatientId(), d.getSymptoms(), d.getDate(), null))
		.collect(Collectors.toList());
	}

	public Optional<Appointment> findAppointmentByDrIdAppointmentId(Long drId, Long appointmentId) {
		manager.debug("finding appointment for doctor id:" + drId + " appointmentId: " + appointmentId);
		// whether appointment is belongs to requested dr or not.
		Optional<Appointment> data = this.appointmentDAO.findById(appointmentId);
		var result = data.filter(d -> drId == d.getDoctor().getDoctorId())
		    .filter(d -> AppointmentStatus.BOOKED.equals(d.getAppointmentStatus()))
		    .map(d ->  {
		    	d.setAppointmentStatus(AppointmentStatus.CANCELLED);
		    	return this.appointmentDAO.updateAppointment(d);
		    })
		    .orElseGet(() -> Optional.empty());
		 System.out.println("is apointment found? " + result.isPresent());
		 return result;
		/*
		manager.debug("data found for appointmentid: " + appointmentId + " drId: " + drId + " " + data.isPresent());
		data.orElseThrow(() -> new NoAppointmentFoundException("Invalid dr=" + drId + " appointmentId="+appointmentId));
		
		
		Appointment app = data.get();
		if(!AppointmentStatus.BOOKED.equals(app.getAppointmentStatus())) {
			manager.debug("appointment for doctor id:" + drId + " appointmentId: " + appointmentId +
					" is either cancelled or visiyed");
			throw new AppointmentCancelledException(appointmentId + " already cancelled or visited");
		}
		
		manager.debug("setting appointment status as cancelled");
		app.setAppointmentStatus(AppointmentStatus.CANCELLED);
		return this.appointmentDAO.updateAppointment(app);
		*/
	}

	
   public Optional<AppointmentDTO> bookSlotIfAvailable(Doctor doctor, Patient patient,
			LocalDateTime appointmentTime, List<String> symptoms) {
		Optional<AppointmentProjection> appointment = this.appointmentDAO.findAppointment(doctor.getDoctorId(),
				patient.getPatientId(),
				appointmentTime);
		if (appointment.isEmpty()) {
			Appointment app = new Appointment(appointmentTime, doctor, patient, symptoms);
			Optional<Appointment> booked = this.appointmentDAO.bookAppointment(app);
			return booked.map(bk -> new AppointmentDTO(bk.getAppointmentId(), bk.getAppointmentStatus().toString(),
					bk.getDoctor().getDoctorId(),
					bk.getPatient().getPatientId(), bk.getSymptoms(), bk.getDate(), bk.getDoctor().getName()));
		}
		return Optional.empty();
	}

public Optional<AppointmentProjection> cancelAppointmentByPatient(String pId, Long aId) {
	Optional<AppointmentProjection> app = this.appointmentDAO.findByPatientAndAppointment(pId, aId, 
														AppointmentStatus.BOOKED.name());
	if (app.isPresent()) {
		System.out.println(app.get().getPatientId());
		this.appointmentDAO.cancelAppointment(pId, aId, AppointmentStatus.CANCELLED.name());
		return app;
	}
	return Optional.empty();
}

}



