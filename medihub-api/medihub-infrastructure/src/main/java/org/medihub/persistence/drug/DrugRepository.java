package org.medihub.persistence.drug;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<DrugJpaEntity, Long> {
}
