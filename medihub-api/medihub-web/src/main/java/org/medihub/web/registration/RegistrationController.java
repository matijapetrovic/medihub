package org.medihub.web.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.patient.GetRegistrationRequestsOutput;
import org.medihub.application.ports.incoming.patient.GetRegistrationRequestsQuery;
import org.medihub.application.ports.incoming.patient.RegisterPatientUseCase;
import org.medihub.application.ports.incoming.patient.RegisterPatientUseCase.RegisterPatientCommand;
import org.medihub.domain.patient.RegistrationRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RestController
@RequestMapping(value="/registration", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterPatientUseCase registerUseCase;
    private final GetRegistrationRequestsQuery getRegistrationRequestsQuery;

    @PostMapping("")
    public ResponseEntity<RegistrationRequest> register(@RequestBody RegisterRequest request) throws AccountNotFoundException {
        RegisterPatientCommand command = createCommand(request);
        RegistrationRequest entity = registerUseCase.registerPatient(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(location).body(entity);
    }

    @GetMapping("")
    ResponseEntity<List<GetRegistrationRequestsOutput>> getRequests() {
        return ResponseEntity.ok(getRegistrationRequestsQuery.getRequests());
    }

    private RegisterPatientCommand createCommand(RegisterRequest request) {
        return new RegisterPatientCommand(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getTelephoneNum(),
                request.getInsuranceNum());
    }
}
