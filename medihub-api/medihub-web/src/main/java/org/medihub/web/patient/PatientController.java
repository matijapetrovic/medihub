package org.medihub.web.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.patient.PatientResponse;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
    private final GetPatientsPort getPatientsPort;

    @GetMapping("")
    @ResponseBody
    public List<PatientResponse> getAll(){
        return loadAll();
    }

    private List<PatientResponse> loadAll(){
        return getPatientsPort.
                getAllPatients().
                stream().
                map(patient -> new PatientResponse(
                        patient.getId(),
                        patient.getPersonalInfo().getFirstName(),
                        patient.getPersonalInfo().getLastName(),
                        patient.getPersonalInfo().getAccount().getEmail(),
                        patient.getPersonalInfo().getAddress(),
                        patient.getInsuranceNumber()
                )).collect(Collectors.toList());
    }
}
