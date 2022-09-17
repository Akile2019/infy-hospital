package com.infy.hospital.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infy.hospital.demo.model.Patient;
import com.infy.hospital.demo.projection.PatientProjection;
import com.infy.hospital.demo.repository.PatientRepository;

@Component
public class PatinentDAO {
	
	@Autowired
	private PatientRepository patientRepository;

	public Optional<Patient> fetchPatient(String patientId) {
		return this.patientRepository.findById(patientId);	
	}

}
