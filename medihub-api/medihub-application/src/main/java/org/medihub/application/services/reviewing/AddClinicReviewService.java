package org.medihub.application.services.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.ports.incoming.reviewing.AddClinicReviewUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.reviewing.LoadClinicReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveClinicReviewPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.domain.patient.Patient;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class AddClinicReviewService implements AddClinicReviewUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final SaveClinicReviewPort saveClinicReviewPort;
    private final LoadClinicReviewPort loadClinicReviewPort;

    @Override
    @Transactional
    public void addClinicReview(AddClinicReviewCommand command) throws ForbiddenException, NotAvailableException {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());
        ClinicReview clinicReview = loadClinicReviewPort.loadById(command.getId());

        ensurePatientCanAddReview(patient, clinicReview);

        clinicReview.updateRating(command.getRating());
        saveClinicReviewPort.saveClinicReview(clinicReview);
    }

    private void ensurePatientCanAddReview(Patient patient, ClinicReview clinicReview) throws ForbiddenException, NotAvailableException {
        if (clinicReview == null
                || !clinicReview.getPatient().getId().equals(patient.getId()))
            throw new ForbiddenException("Patient is not allowed to review clinic");
        if (!clinicReview.getCanReview())
            throw new NotAvailableException("Patient can not review clinic");
    }
}
