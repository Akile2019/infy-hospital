package com.infy.hospital.demo.projection;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface AppointmentProjection {
	
	Long getAppointmentId();
	Long getDoctorId();
	String getPatientId();
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
	LocalDateTime getDate();
	List<String> getSymptoms();
	String getAppointmentStatus();

}