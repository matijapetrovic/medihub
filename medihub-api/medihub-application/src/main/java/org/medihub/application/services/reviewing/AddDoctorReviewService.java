package org.medihub.application.services.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.reviewing.AddDoctorReviewUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.finished_appointment.LoadFinishedAppointmentPort;
import org.medihub.application.ports.outgoing.reviewing.SaveDoctorReviewPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;

@RequiredArgsConstructor
public class AddDoctorReviewService implements AddDoctorReviewUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadFinishedAppointmentPort loadFinishedAppointmentPort;
    private final SaveDoctorReviewPort saveDoctorReviewPort;

    @Override
    public void addDoctorReview(AddDoctorReviewCommand command) throws ForbiddenException {
        Account account = getAuthenticatedPort.getAuthenticated();
        FinishedAppointment appointment =
                loadFinishedAppointmentPort.loadFinishedAppointment(command.getAppointmentId());
        ensurePatientCanAddReview(account, appointment);

        MedicalDoctorReview doctorReview = new MedicalDoctorReview(
                null,
                command.getRating(),
                appointment,
                appointment.getAppointment().getDoctor());
        saveDoctorReviewPort.saveDoctorReview(doctorReview);
    }

    private void ensurePatientCanAddReview(Account account, FinishedAppointment appointment) throws ForbiddenException {
        if (!appointment.getAppointment().getPatient().getAccount().getId().equals(account.getId())) {
            throw new ForbiddenException();
        }
    }
}
