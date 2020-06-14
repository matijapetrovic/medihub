package org.medihub.persistence.appointment_type;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentTypeJpaEntity, Long> {

    Optional<AppointmentTypeJpaEntity> findById(Long id);

    @Query("select count(at) from AppointmentTypeJpaEntity at " +
            "where at.id=:id and at in (select d.specialization from MedicalDoctorJpaEntity d where d.specialization.id=:id) ")
    Long countAppointmentTypeSpecializations(@Param(value="id") Long id);

    @Modifying
    @Query(value="DELETE FROM clinic_appointment_type_mapping where appointment_type_id=:id", nativeQuery=true)
    void deleteClinicPrices(@Param(value="id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout", value = "0")})
    @Modifying
    @Query("delete from AppointmentTypeJpaEntity at where at.id=:id and " +
            "at not in (select d.specialization from MedicalDoctorJpaEntity d where d.specialization.id=:id)")
    void deleteAppointmentTypeWithLock(@Param(value="id") Long id);
}
