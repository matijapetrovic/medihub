package org.medihub.persistence.personal_info;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.account.Address;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.persistence.account.AccountMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalInfoMapper {
    private final AccountMapper accountMapper;

    public PersonalInfo mapToDomainEntity(PersonalInfoJpaEntity personalInfo) {
        return new PersonalInfo(
                personalInfo.getId(),
                personalInfo.getFirstName(),
                personalInfo.getLastName(),
                new Address(
                        personalInfo.getAddress(),
                        personalInfo.getCity(),
                        personalInfo.getCountry()),
                personalInfo.getTelephoneNumber(),
                accountMapper.mapToDomainEntity(personalInfo.getAccount()));
    }

    public PersonalInfoJpaEntity mapToJpaEntity(PersonalInfo personalInfo) {
        return new PersonalInfoJpaEntity(
                personalInfo.getId(),
                personalInfo.getFirstName(),
                personalInfo.getLastName(),
                personalInfo.getAddress(),
                personalInfo.getCity(),
                personalInfo.getCountry(),
                personalInfo.getTelephoneNumber(),
                accountMapper.mapToJpaEntity(personalInfo.getAccount()));
    }
}
