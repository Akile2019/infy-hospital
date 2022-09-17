package com.infy.hospital.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infy.hospital.demo.model.Doctor;
import com.infy.hospital.demo.projection.AppointmentProjection;
import com.infy.hospital.demo.projection.DoctorProjection;



@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query(value="select name, doctor_id as doctorId, specialty from doctor", nativeQuery = true)
	List<DoctorProjection> findAllByName();
	
}
