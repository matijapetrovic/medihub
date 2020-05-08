package org.medihub.persistence.medical_doctor;

import org.medihub.domain.MedicalDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalDoctorRepository extends JpaRepository<MedicalDoctorJpaEntity, Long> {
    Optional<MedicalDoctorJpaEntity> findById(Long id);
    List<MedicalDoctorJpaEntity> findAllByClinicId(Long clinicId);
}
