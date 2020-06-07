package org.medihub.persistence.predefined_appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredefinedAppointmentRepository extends JpaRepository<PredefinedAppointmentJpaEntity, Long> {
    List<PredefinedAppointmentJpaEntity> findAllByMedicalDoctor_Clinic_Id(Long clinicId);
}
