package com.infy.hospital.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infy.hospital.demo.model.Doctor;
import com.infy.hospital.demo.projection.DoctorProjection;
import com.infy.hospital.demo.repository.DoctorRepository;

@Component
public class DoctorDAO {
	
	@Autowired
	private DoctorRepository doctorRepository;

	public boolean hasDoctorExist(Long doctorId) {
		return this.doctorRepository.existsById(doctorId);
	}

	public List<DoctorProjection> getAllDoctors() {
		return this.doctorRepository.findAllByName();
	}

	public Optional<Doctor> fetchDoctor(Long doctorId) {
		return this.doctorRepository.findById(doctorId);
	}

}
