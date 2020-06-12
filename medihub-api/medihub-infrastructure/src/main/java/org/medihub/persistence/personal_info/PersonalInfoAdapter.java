package org.medihub.persistence.personal_info;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.profile.LoadPersonalInfoPort;
import org.medihub.application.ports.outgoing.profile.SavePersonalInfoPort;
import org.medihub.domain.account.PersonalInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonalInfoAdapter implements
        LoadPersonalInfoPort,
        SavePersonalInfoPort {
    private final PersonalInfoRepository repository;
    private final PersonalInfoMapper mapper;

    @Override
    public PersonalInfo loadPersonalInfoByAccountId(Long accountId) throws NotFoundException {
        Optional<PersonalInfoJpaEntity> personalInfo =
                repository.findByAccountId(accountId);

        if (personalInfo.isEmpty())
            throw new NotFoundException("Account not found");

        return mapper.mapToDomainEntity(personalInfo.get());
    }

    @Override
    public void savePersonalInfo(PersonalInfo personalInfo) {
        repository.save(mapper.mapToJpaEntity(personalInfo));
    }
}
