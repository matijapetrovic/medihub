package org.medihub.web.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.*;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase.AddClinicCommand;
import org.medihub.application.ports.incoming.clinic.UpdateClinicUseCase.UpdateClinicCommand;
import org.medihub.domain.clinic.Clinic;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Component
@RestController
@RequestMapping(value = "/clinic", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClinicController {
    private final AddClinicUseCase addClinicUseCase;
    private final SearchClinicsQuery searchClinicsQuery;
    private final GetClinicNamesQuery getClinicNamesQuery;
    private final GetCurrentClinicUseCase getCurrentClinicUseCase;
    private final UpdateClinicUseCase updateClinicUseCase;

    @GetMapping("")
    ResponseEntity<List<SearchClinicsOutput>> searchClinics(@RequestParam(required = false)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate date,
                                                            @RequestParam(required = false) Long appointmentTypeId)  {
        return ResponseEntity.ok(searchClinicsQuery.searchClinics(date, appointmentTypeId));
    }

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

    @GetMapping("/names")
    ResponseEntity<List<GetClinicNamesOutput>> getClinicNames() {
        return ResponseEntity.ok(getClinicNamesQuery.getClinicNames());
    }

    @GetMapping("/getCurrent")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    ResponseEntity<?> getClinic() {
        return ResponseEntity.ok(getCurrentClinicUseCase.getCurrentClinic());
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void updateClinic(@RequestBody UpdateClinicRequest updateClinicRequest) {
        UpdateClinicUseCase.UpdateClinicCommand updateClinicCommand = createUpdateCommand(updateClinicRequest);
        updateClinicUseCase.updateClinic(updateClinicCommand);
    }

    private UpdateClinicCommand createUpdateCommand(UpdateClinicRequest updateClinicRequest) {
        return new UpdateClinicCommand(
                updateClinicRequest.getName(),
                updateClinicRequest.getAddressLine(),
                updateClinicRequest.getCity(),
                updateClinicRequest.getCountry(),
                updateClinicRequest.getDescription()
        );
    }
}
