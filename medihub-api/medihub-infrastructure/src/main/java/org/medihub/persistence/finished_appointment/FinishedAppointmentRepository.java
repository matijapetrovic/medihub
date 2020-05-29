package org.medihub.persistence.finished_appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface FinishedAppointmentRepository extends JpaRepository<FinishedAppointmentJpaEntity, Long> {
    List<FinishedAppointmentJpaEntity> findAllByAppointment_Patient_Id(Long patientId);

    @Query("select fa " +
            "from FinishedAppointmentJpaEntity fa " +
            "where fa.appointment.doctor.clinic.id=:clinicId")
    List<FinishedAppointmentJpaEntity> findAllByAppointment_Doctor_Clinic_Id(@Param(value="clinicId") Long clinicId);
}
