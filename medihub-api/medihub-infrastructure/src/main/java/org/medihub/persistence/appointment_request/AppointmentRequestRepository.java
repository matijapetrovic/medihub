package org.medihub.persistence.appointment_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequestJpaEntity, Long> {
    List<AppointmentRequestJpaEntity> findAllByDoctorClinicIdAndDoctorArchivedFalse(Long clinicId);
    List<AppointmentRequestJpaEntity> findAllByDoctorArchivedFalse();
}
