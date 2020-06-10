package org.medihub.application.services.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.reviewing.GetDoctorsForReviewOutput;
import org.medihub.application.ports.incoming.reviewing.GetDoctorsForReviewQuery;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.reviewing.GetDoctorReviewsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.domain.patient.Patient;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetDoctorsForReviewService implements GetDoctorsForReviewQuery {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final GetDoctorReviewsPort getDoctorReviewsPort;
    @Override
    public List<GetDoctorsForReviewOutput> getDoctorsForReview() {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());

        List<MedicalDoctorReview> doctorReviews = getDoctorReviewsPort.getDoctorReviewsForReview(patient.getId());
        return mapToOutput(doctorReviews);
    }

    private List<GetDoctorsForReviewOutput> mapToOutput(List<MedicalDoctorReview> doctorReviews) {
        return doctorReviews
                .stream()
                .map(medicalDoctorReview -> new GetDoctorsForReviewOutput(
                        medicalDoctorReview.getId(),
                        medicalDoctorReview.getDoctor().getFullName()
                ))
                .collect(Collectors.toList());
    }
}
