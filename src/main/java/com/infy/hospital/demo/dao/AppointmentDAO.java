package com.infy.hospital.demo.dao;

import java.time.LocalDateTime;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infy.hospital.demo.dto.AppointmentDTO;
import com.infy.hospital.demo.model.Appointment;
import com.infy.hospital.demo.model.AppointmentStatus;
import com.infy.hospital.demo.projection.AppointmentProjection;
import com.infy.hospital.demo.repository.AppointmentRepository;
import com.infy.hospital.demo.repository.DoctorRepository;

@Component
public class AppointmentDAO {
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	public List<AppointmentProjection> findAllAppointmentsByDoctorId(Long id) {
		return this.appointmentRepository.findAllAppointmentsByDoctorId(id);
	}

	public List<AppointmentProjection> findAllAppointmentsByDoctorIdAndStatus(Long id, String status) {
		return this.appointmentRepository.findAllAppointmentsByDoctorIdAndStatus(id, status);
	}

	public Optional<Appointment> findById(Long appointmentId) {
		return this.appointmentRepository.findById(appointmentId);
	}

	public Optional<Appointment> updateAppointment(Appointment app) {
		return Optional.ofNullable(appointmentRepository.save(app));
	}

	public Optional<AppointmentProjection> findAppointment(Long doctorId, String string, LocalDateTime appointmentTime) {
		LocalDateTime from = appointmentTime.plusMinutes(30);
		LocalDateTime to = appointmentTime.truncatedTo(ChronoUnit.MINUTES);
		return this.appointmentRepository.findAppointment(doctorId, to, from);
	}

	public Optional<Appointment> bookAppointment(Appointment appointment) {

		return Optional.ofNullable(this.appointmentRepository.save(appointment));
	}

	public Optional<AppointmentProjection> findByPatientAndAppointment(String pId, Long aId, String status) {
		return this.appointmentRepository.findAppointmentByPatientAndAppId(pId, aId, status);
	}

	public void cancelAppointment(String pId, Long aId, String name) {
		this.appointmentRepository.cancelAppointment(pId, aId, AppointmentStatus.CANCELLED.name());
	}

}
