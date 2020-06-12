package org.medihub.application.ports.outgoing.registration_request;

public interface DeleteRegistrationRequestPort {
    void deleteById(Long requestId);
}
