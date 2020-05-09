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

    List<ClinicRoomJpaEntity> findAllByClinic_Id(Long clinicId);
}