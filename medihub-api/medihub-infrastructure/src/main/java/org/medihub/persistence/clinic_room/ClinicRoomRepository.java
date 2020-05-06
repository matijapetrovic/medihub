package org.medihub.persistence.clinic_room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ClinicRoomRepository extends JpaRepository<ClinicRoomJpaEntity, Long> {
    Optional<ClinicRoomJpaEntity> findByName(String name);

    @Transactional
    void deleteClinicRoomByName(String name);

    @Query("select id, name from ClinicRoomJpaEntity where clinic_id = :clinicId")
    List<ClinicRoomJpaEntity> findAllByClinicId(@Param("clinicId") Long clinicId);
}