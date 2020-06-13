package org.medihub.application.services.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.registration.RejectRegistrationUseCase;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.registration_request.DeleteRegistrationRequestPort;
import org.medihub.application.ports.outgoing.registration_request.LoadRegistrationRequestPort;
import org.medihub.domain.patient.RegistrationRequest;


@RequiredArgsConstructor
public class RejectRegistrationService implements RejectRegistrationUseCase {
    private final LoadRegistrationRequestPort loadRegistrationRequestPort;
    private final DeleteRegistrationRequestPort deleteRegistrationRequestPort;
    private final SendEmailPort sendEmailPort;

    @Override
    public void rejectRegistration(RejectRegistrationCommand command) throws NotFoundException {
        RegistrationRequest request = loadRegistrationRequestPort.loadById(command.getRequestId());
        deleteRegistrationRequestPort.deleteById(request.getId());
        notifyUser(command.getReason());
    }

    private void notifyUser(String reason) {
        String to = "medihub.mail@gmail.com";
        String subject = "Registration request rejected";
        String text = String.format("Your registration request has been rejected. Reason:\n%s",
                reason);
        sendEmailPort.sendEmail(to, subject, text);
    }
}
