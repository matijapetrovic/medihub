package org.medihub.web.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.registration.*;
import org.medihub.application.ports.incoming.registration.RegisterPatientUseCase.RegisterPatientCommand;
import org.medihub.application.ports.incoming.registration.RejectRegistrationUseCase.RejectRegistrationCommand;
import org.medihub.domain.patient.RegistrationRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final RejectRegistrationUseCase rejectRegistrationUseCase;
    private final AcceptRegistrationUseCase acceptRegistrationUseCase;

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

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_CLINIC_CENTER_ADMIN')")
    public ResponseEntity<List<GetRegistrationRequestsOutput>> getRequests() {
        return ResponseEntity.ok(getRegistrationRequestsQuery.getRequests());
    }

    @PostMapping("/{requestId}/accept")
    @PreAuthorize("hasRole('ROLE_CLINIC_CENTER_ADMIN')")
    public void accept(@PathVariable Long requestId) throws NotFoundException {
        acceptRegistrationUseCase.acceptRegistration(requestId);
    }

    @PostMapping("/{requestId}/reject")
    @PreAuthorize("hasRole('ROLE_CLINIC_CENTER_ADMIN')")
    public void reject(@PathVariable Long requestId, @RequestBody RejectionRequest requestBody) throws NotFoundException {
        RejectRegistrationCommand command = new RejectRegistrationCommand(requestId, requestBody.getReason());
        rejectRegistrationUseCase.rejectRegistration(command);
    }
}
