package org.medihub.persistence.drug;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.drugs.SaveDrugPort;
import org.medihub.domain.Drug;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrugAdapter implements SaveDrugPort {
    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;

    @Override
    public Drug saveDrug(Drug drug) {
        DrugJpaEntity jpaDrug = drugRepository.save(drugMapper.mapToJpaEntity(drug));
        return drugMapper.mapToDomainEntity(jpaDrug);
    }
}
