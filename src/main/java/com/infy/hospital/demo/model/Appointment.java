package com.infy.hospital.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Entity
@Builder
@AllArgsConstructor
public class Appointment implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3324042769215365037L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointmentId", updatable = false, nullable = false)
	private Long appointmentId;
	 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Convert(converter = ListToStringConverter.class)
	private List<String> symptoms;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	
	
	public Appointment() {
		super();
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Appointment(LocalDateTime date, Doctor doctor, Patient patient, List<String> symptoms) {
		super();
		this.date = date;
		this.doctor = doctor;
		this.patient = patient;
		this.symptoms = symptoms;
		this.appointmentStatus = AppointmentStatus.BOOKED;
	}
	
	
	
	
}

