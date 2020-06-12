package org.medihub.application.ports.outgoing.profile;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.account.PersonalInfo;

public interface LoadPersonalInfoPort {
    PersonalInfo loadPersonalInfoByAccountId(Long accountId) throws NotFoundException;
}
