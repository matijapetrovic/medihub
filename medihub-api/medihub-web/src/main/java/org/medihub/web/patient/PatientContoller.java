package org.medihub.web.patient;

import lombok.RequiredArgsConstructor;
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
public class PatientContoller {
    private final GetPatientsPort getPatientsPort;

    @GetMapping("")
    @ResponseBody
    public List<?> getAll(){
        return loadAll();
    }

    private List<?> loadAll(){
        return getPatientsPort.
                getAllPatients().
                stream().
                map(patient -> new PatientResponse(
                        patient.getAccount().getPersonalInfo().getFirstName(),
                        patient.getAccount().getPersonalInfo().getLastName(),
                        patient.getAccount().getEmail(),
                        patient.getAccount().getAddress(),
                        patient.getInsuranceNumber()
                )).collect(Collectors.toList());
    }
}
