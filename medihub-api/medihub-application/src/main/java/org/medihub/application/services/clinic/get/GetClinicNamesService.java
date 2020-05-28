package org.medihub.application.services.clinic.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.GetClinicNamesOutput;
import org.medihub.application.ports.incoming.clinic.GetClinicNamesQuery;
import org.medihub.application.ports.outgoing.clinic.GetClinicNamesPort;
import org.medihub.domain.clinic.Clinic;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetClinicNamesService implements GetClinicNamesQuery {
    private final GetClinicNamesPort getClinicNamesPort;

    @Override
    public List<GetClinicNamesOutput> getClinicNames() {
        return mapToOutput(getClinicNamesPort.getClinicNames());
    }

    private List<GetClinicNamesOutput> mapToOutput(List<Clinic> clinics) {
        return clinics
                .stream()
                .map((clinic) ->
                    new GetClinicNamesOutput(
                            clinic.getId(),
                            clinic.getName()
                    )
                )
                .collect(Collectors.toList());
    }
}
