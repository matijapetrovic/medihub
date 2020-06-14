package org.medihub.application.services.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.ports.incoming.reviewing.AddDoctorReviewUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.reviewing.LoadDoctorReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveDoctorReviewPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.domain.patient.Patient;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class AddDoctorReviewService implements AddDoctorReviewUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final SaveDoctorReviewPort saveDoctorReviewPort;
    private final LoadDoctorReviewPort loadDoctorReviewPort;

    @Override
    @Transactional
    public void addDoctorReview(AddDoctorReviewCommand command) throws ForbiddenException, NotAvailableException {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());
        MedicalDoctorReview doctorReview = loadDoctorReviewPort.loadByIdWithLock(command.getId());

        ensurePatientCanAddReview(patient, doctorReview);

        doctorReview.updateRating(command.getRating());
        saveDoctorReviewPort.saveDoctorReview(doctorReview);
    }

    private void ensurePatientCanAddReview(Patient patient, MedicalDoctorReview doctorReview) throws ForbiddenException, NotAvailableException {
        if (doctorReview == null
                || !doctorReview.getPatient().getId().equals(patient.getId()))
            throw new ForbiddenException("Patient is not allowed to review doctor");
        if (!doctorReview.getCanReview())
            throw new NotAvailableException("Patient can not review doctor");
    }
}
