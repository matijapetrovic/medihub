package org.medihub.web.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.patient.LoadPatientUseCase;
import org.medihub.application.ports.incoming.patient.PatientResponse;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.domain.patient.Patient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
    private final LoadPatientUseCase loadPatientUseCase;

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
                        patient.getAccount().getPersonalInfo().getFirstName(),
                        patient.getAccount().getPersonalInfo().getLastName(),
                        patient.getAccount().getEmail(),
                        patient.getAccount().getAddress(),
                        patient.getInsuranceNumber()
                )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(loadPatientUseCase.loadPatientById(id));
    }
}
