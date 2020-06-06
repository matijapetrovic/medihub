package org.medihub.persistence.finished_appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface FinishedAppointmentRepository extends JpaRepository<FinishedAppointmentJpaEntity, Long> {
    List<FinishedAppointmentJpaEntity> findAllByAppointment_Patient_Id(Long patientId);
    List<FinishedAppointmentJpaEntity> findAllByAppointment_Doctor_Clinic_Id(Long clinicId);

}
