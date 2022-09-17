package com.infy.hospital.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Patient implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6595770219922348106L;

	@Id
	@Column(name = "patientId", updatable = false, nullable = false)
	private String patientId;

	@Column(nullable = false, name = "name")
	private String name;
	
	@Column(nullable = false, name = "birthday")
	private Date birthday;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "patient")
	private List<Appointment> appointments;

	public Patient() {
		super();
	}
}