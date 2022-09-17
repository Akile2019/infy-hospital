package com.infy.hospital.demo.dto;

public class DoctorDTO {
	private Long doctorId;
	private String name;
	private String specialty;

	public DoctorDTO(Long doctorId, String name, String specialty) {
		this.doctorId = doctorId;
		this.name = name;
		this.specialty = specialty;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public String getName() {
		return name;
	}

	public String getSpecialty() {
		return specialty;
	}
	
	

}
