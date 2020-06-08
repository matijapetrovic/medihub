package org.medihub.persistence.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<PrescriptionJpaEntity, Long> {

    @Query(value = "SELECT p " +
            "FROM finished_appointment fa " +
            "INNER JOIN prescriptions p " +
            "ON fa.id = p.finished_appointment_id " +
            "INNER JOIN drug d " +
            "ON p.drug_id = d.id " +
            "WHERE p.medical_nurse_id is null ", nativeQuery=true)
    public List<PrescriptionJpaEntity> getClinicPrescriptions(@Param("clinic_id") Long clinicId);
}
