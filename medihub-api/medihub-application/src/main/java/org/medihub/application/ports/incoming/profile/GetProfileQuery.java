package org.medihub.application.ports.incoming.profile;

import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.exceptions.NotFoundException;

public interface GetProfileQuery {
    GetProfileOutput getProfile(String email) throws AccountNotFoundException, NotFoundException;
}
