package com.infy.hospital.demo.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.infy.hospital.demo.model.AppointmentStatus;

public class AppointmentDTO {
	
	private Long appointmentId;
	private String appointmentStatus;
	private LocalDateTime date;
	private Long doctorId;
	private String patientId;
	private List<String> symptoms;
	private String name;

	public AppointmentDTO(Long appointmentId, String appointmentStatus, Long doctorId, String patientId,
			List<String> symptoms, LocalDateTime localDateTime, String name) {
		this.appointmentId = appointmentId;
		this.appointmentStatus = appointmentStatus;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.symptoms = symptoms;
		this.date = localDateTime;
		this.name = name;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	
	public String getName() {
		return name;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public String getPatientId() {
		return patientId;
	}

	public List<String> getSymptoms() {
		return symptoms;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	

}
