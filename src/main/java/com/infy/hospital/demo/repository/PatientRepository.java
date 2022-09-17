package com.infy.hospital.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infy.hospital.demo.model.Patient;
import com.infy.hospital.demo.projection.PatientProjection;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

	@Query(value = "select patient_id as patientId, name, birthday from patient where patient_id = :patientId", nativeQuery = true)
	Optional<PatientProjection> fetchPatient(String patientId);
}
