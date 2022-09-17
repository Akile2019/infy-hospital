package com.infy.hospital.demo.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppointmentBookingRequest {
	
	private String patientId;
	private String patientName;
	private String doctorName;
	private Long doctorId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
	private LocalDateTime appointmentTime;
	private List<String> symptoms;
	
	
	 public AppointmentBookingRequest(String patientId, String patientName, String doctorName, Long doctorId,
			LocalDateTime appointmentTime, List<String> symptoms) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.doctorId = doctorId;
		this.appointmentTime = appointmentTime;
		this.symptoms = symptoms;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public LocalDateTime getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(LocalDateTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public List<String> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}
	
	@Override
	public String toString() {
		return "AppointmentBookingRequest [patientId=" + patientId + ", patientName=" + patientName + ", doctorName="
				+ doctorName + ", doctorId=" + doctorId + ", appointmentTime=" + appointmentTime + ", symptoms="
				+ symptoms + "]";
	}
	
	
	

}
