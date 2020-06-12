package org.medihub.application.ports.outgoing.profile;

import org.medihub.domain.account.PersonalInfo;

public interface SavePersonalInfoPort {
    void savePersonalInfo(PersonalInfo personalInfo);
}
