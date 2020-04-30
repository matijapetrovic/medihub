package org.medihub.web.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase.AddClinicCommand;
import org.medihub.domain.Clinic;
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
@RequestMapping(value = "/clinic", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClinicController {
    private final AddClinicUseCase addClinicUseCase;

    @PostMapping("")
    ResponseEntity<?> addClinic(@RequestBody AddClinicRequest request) {
        AddClinicCommand command = new AddClinicCommand(
                request.getName(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getDescription());

        Clinic entity = addClinicUseCase.addClinic(command);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
