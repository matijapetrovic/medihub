package org.medihub.web.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.LoadPatientPort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientContoller {
    private final LoadPatientPort loadPatientPort;

    @GetMapping("/get/all")
    public List<?> getAall(){

        return loadPatientPort.
                loadAllPatients().
                stream().
                map(patient -> new PatientResponse(
                        patient.getAccount().getPersonalInfo().getFirstName(),
                        patient.getAccount().getPersonalInfo().getLastName(),
                        patient.getAccount().getEmail(),
                        patient.getAccount().getAddress()
                        )).collect(Collectors.toList());
    }
}
