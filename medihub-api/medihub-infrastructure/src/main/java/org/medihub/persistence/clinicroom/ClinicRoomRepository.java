package org.medihub.persistence.clinicroom;

import org.medihub.persistence.account.AccountJpaEntity
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicRoomRespository extends JpaRepository<ClinicRoomJpaEntity, String> {
    Optional<ClinicRoomJpaEntity> findByName(String name);
}