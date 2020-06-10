package org.medihub.application.services.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
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
    public void addDoctorReview(AddDoctorReviewCommand command) throws ForbiddenException {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());
        MedicalDoctorReview doctorReview = loadDoctorReviewPort.loadByPatientIdAndDoctorId(patient.getId(),
                command.getId());

        ensurePatientCanAddReview(doctorReview);

        doctorReview.updateRating(command.getRating());
        saveDoctorReviewPort.saveDoctorReview(doctorReview);
    }

    private void ensurePatientCanAddReview(MedicalDoctorReview doctorReview) throws ForbiddenException {
        if (doctorReview == null || !doctorReview.getCanReview())
            throw new ForbiddenException("Patient cannot review clinic");
    }
}
