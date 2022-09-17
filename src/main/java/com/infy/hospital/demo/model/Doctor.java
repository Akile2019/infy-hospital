package com.infy.hospital.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

@Entity
@Table(name="doctor")
public class Doctor implements Serializable  {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(nullable = false, name = "doctorId", updatable = false)
 private Long doctorId;
 
 @Column(nullable = false, name = "name")
 private String name;
 
 @Enumerated(EnumType.STRING)
 private Specialty specialty;
 
 @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, 
		 	mappedBy = "doctor", orphanRemoval = true)
 private List<Appointment> appointments;

	public Doctor() {
		super();
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}