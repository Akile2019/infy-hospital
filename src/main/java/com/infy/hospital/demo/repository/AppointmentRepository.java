package com.infy.hospital.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infy.hospital.demo.dto.AppointmentDTO;
import com.infy.hospital.demo.model.Appointment;
import com.infy.hospital.demo.projection.AppointmentProjection;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(value = "select appointment_id as appointmentId, patient_id as patientId, "
			+ "doctor_id as doctorId, date as date, symptoms, "
			+ " appointment_status as appointmentStatus  from appointment"
			+ " where doctor_id=:id", nativeQuery = true)
	List<AppointmentProjection> findAllAppointmentsByDoctorId(Long id);
	
	@Query(value = "select appointment_id as appointmentId, patient_id as patientId, "
			+ "doctor_id as doctorId, date as date, symptoms, appointment_status as appointmentStatus from appointment"
			+ " where doctor_id=:id and appointment_status =:status", nativeQuery = true)
	List<AppointmentProjection> findAllAppointmentsByDoctorIdAndStatus(Long id, String status);

	@Query(value = "select appointment_id as appointmentId, patient_id as patientId, "
			+ "doctor_id as doctorId, date as date, symptoms, appointment_status as appointmentStatus"
			+ " from appointment"
			+ " where doctor_id=:doctorId and appointment_id =:appointmentId", nativeQuery = true)
	Optional<AppointmentProjection> findByAppointmentIdAndDoctorId(Long appointmentId, Long doctorId);

	@Query(value = "select appointment_id as appointmentId, patient_id as patientId, "
			+ "doctor_id as doctorId, date as date, symptoms, "
			+ " appointment_status as appointmentStatus  from appointment"
			+ " where doctor_id=:id and  date >= :appointmentTime and date < :from", nativeQuery = true)
	Optional<AppointmentProjection> findAppointment(Long id, LocalDateTime appointmentTime, LocalDateTime from);

	@Modifying
	@Query(value = "update appointment set appointment_status = :status "
			+ " where appointment_id = :aId and patient_id=:id", nativeQuery = true)
	void cancelAppointment(String id, Long aId, String status);

	@Query(value = "select appointment_id as appointmentId, patient_id as patientId, "
			+ "doctor_id as doctorId, date as date, symptoms, "
			+ " appointment_status as appointmentStatus  from appointment"
			+ " where patient_id=:id and  appointment_id = :appId and appointment_status = :status", nativeQuery = true)
	Optional<AppointmentProjection> findAppointmentByPatientAndAppId(String id, Long appId, String status);
	
}