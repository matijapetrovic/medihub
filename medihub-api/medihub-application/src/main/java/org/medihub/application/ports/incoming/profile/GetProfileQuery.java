package org.medihub.application.ports.incoming.profile;

import org.medihub.application.exceptions.AccountNotFoundException;

public interface GetProfileQuery {
    GetProfileOutput getProfile(String email) throws AccountNotFoundException;
}