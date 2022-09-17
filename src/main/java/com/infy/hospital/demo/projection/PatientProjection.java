package com.infy.hospital.demo.projection;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface PatientProjection {
	String getPatientId();
	String getName();
	@JsonFormat(pattern = "YYYY-MM-DD")
	LocalDate getBirthday();
}
