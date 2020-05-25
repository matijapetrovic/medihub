package org.medihub.persistence.drug;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.drugs.GetDrugsQuery;
import org.medihub.application.ports.outgoing.drugs.GetDrugsPort;
import org.medihub.application.ports.outgoing.drugs.SaveDrugPort;
import org.medihub.domain.Drug;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DrugAdapter implements SaveDrugPort, GetDrugsPort {
    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;

    @Override
    public Drug saveDrug(Drug drug) {
        DrugJpaEntity jpaDrug = drugRepository.save(drugMapper.mapToJpaEntity(drug));
        return drugMapper.mapToDomainEntity(jpaDrug);
    }

    @Override
    public List<Drug> getDrugs() {
        return drugRepository
                .findAll()
                .stream()
                .map(drugMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
