package org.medihub.persistence.drug;

import org.medihub.domain.Drug;
import org.springframework.stereotype.Component;

@Component
public class DrugMapper {
    public Drug mapToDomainEntity(DrugJpaEntity drugJpaEntity){

        return new Drug(drugJpaEntity.getId(), drugJpaEntity.getName());
    }

    public DrugJpaEntity mapToJpaEntity(Drug drug){
        return new DrugJpaEntity(drug.getId(), drug.getName());
    }
}
