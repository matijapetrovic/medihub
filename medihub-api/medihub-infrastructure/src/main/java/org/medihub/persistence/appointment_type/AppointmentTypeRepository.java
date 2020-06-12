package org.medihub.persistence.appointment_type;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentTypeJpaEntity, Long> {

    Optional<AppointmentTypeJpaEntity> findById(Long id);

    @Query("select count(at) from AppointmentTypeJpaEntity at " +
            "where at.id=:id and at in (select d.specialization from MedicalDoctorJpaEntity d where d.specialization.id=:id) ")
    Long countAppointmentTypeSpecializations(@Param(value="id") Long id);

    @Modifying
    @Query(value="DELETE FROM clinic_appointment_type_mapping where appointment_type_id=:id", nativeQuery=true)
    void deleteClinicPrices(@Param(value="id") Long id);
}
