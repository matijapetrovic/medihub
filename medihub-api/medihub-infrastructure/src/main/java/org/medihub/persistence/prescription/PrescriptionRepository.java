package org.medihub.persistence.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<PrescriptionJpaEntity, Long> {
    List<PrescriptionJpaEntity> findAllByFinishedAppointmentId(Long appointmentId);


    @Query("select pr from PrescriptionJpaEntity pr " +
    "inner join FinishedAppointmentJpaEntity fa on pr.finishedAppointment = fa " +
    "where fa.appointment.doctor.clinic.id = :clinic_id and pr.medicalNurseJpaEntity is null ")
    List<PrescriptionJpaEntity> getClinicPrescriptions(@Param("clinic_id") Long clinicId);
}
