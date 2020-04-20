package org.medihub.web.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.RegisterPatientUseCase;
import org.medihub.application.ports.incoming.RegisterPatientUseCase.RegisterPatientCommand;
import org.medihub.domain.patient.RegistrationRequest;
import org.medihub.web.patient.dto.RegisterRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
@RestController
@RequestMapping(value="/patient", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PatientController {
    private final RegisterPatientUseCase registerUseCase;

    @PostMapping("/registration")
    ResponseEntity<?> register(@RequestBody RegisterRequest request) throws AccountNotFoundException {
        RegisterPatientCommand command =
                new RegisterPatientCommand(
                        request.getEmail(),
                        request.getPassword(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getAddress(),
                        request.getCity(),
                        request.getCountry(),
                        request.getTelephoneNum(),
                        request.getInsuranceNum());

        RegistrationRequest entity = registerUseCase.registerPatient(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
