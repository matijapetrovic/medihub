package org.medihub.application.services.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.reviewing.AddClinicReviewUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.finished_appointment.LoadFinishedAppointmentPort;
import org.medihub.application.ports.outgoing.reviewing.SaveClinicReviewPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.clinic.ClinicReview;

@RequiredArgsConstructor
public class AddClinicReviewService implements AddClinicReviewUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadFinishedAppointmentPort loadFinishedAppointmentPort;
    private final SaveClinicReviewPort saveClinicReviewPort;

    @Override
    public void addClinicReview(AddClinicReviewCommand command) throws ForbiddenException {
        Account account = getAuthenticatedPort.getAuthenticated();
        FinishedAppointment appointment =
                loadFinishedAppointmentPort.loadFinishedAppointment(command.getAppointmentId());
        ensurePatientCanAddReview(account, appointment);

        ClinicReview clinicReview = new ClinicReview(
                null,
                command.getRating(),
                appointment,
                appointment.getAppointment().getDoctor().getClinic());
        saveClinicReviewPort.saveClinicReview(clinicReview);
    }

    private void ensurePatientCanAddReview(Account account, FinishedAppointment appointment) throws ForbiddenException {
        if (!appointment.getAppointment().getPatient().getAccount().getId().equals(account.getId())) {
            throw new ForbiddenException();
        }
    }
}
