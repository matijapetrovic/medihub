package org.medihub.application.services.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.reviewing.GetClinicsForReviewOutput;
import org.medihub.application.ports.incoming.reviewing.GetClinicsForReviewQuery;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.reviewing.GetClinicReviewsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.domain.patient.Patient;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetClinicsForReviewService implements GetClinicsForReviewQuery {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final GetClinicReviewsPort getClinicReviewsPort;

    @Override
    public List<GetClinicsForReviewOutput> getClinicsForReview() {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());

        List<ClinicReview> clinicReviews = getClinicReviewsPort.getClinicsReviewsForReview(patient.getId());
        return mapToOutput(clinicReviews);
    }

    private List<GetClinicsForReviewOutput> mapToOutput(List<ClinicReview> clinicReviews) {
        return clinicReviews
                .stream()
                .map(clinicReview -> new GetClinicsForReviewOutput(
                        clinicReview.getId(),
                        clinicReview.getClinic().getName()))
                .collect(Collectors.toList());
    }
}
