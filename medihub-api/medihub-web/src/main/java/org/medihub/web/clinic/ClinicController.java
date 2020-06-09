package org.medihub.web.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.*;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase.AddClinicCommand;
import org.medihub.domain.Money;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RestController
@RequestMapping(value = "/clinic", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClinicController {
    private final AddClinicUseCase addClinicUseCase;
    private final SearchClinicsQuery searchClinicsQuery;
    private final GetClinicNamesQuery getClinicNamesQuery;
    private final GetClinicProfileQuery getClinicProfileQuery;
    private final GetAppointmentPriceUseCase getAppointmentPriceUseCase;
    private final AddPriceToAppointmentTypeUseCase addPriceToAppointmentTypeUseCase;
    private final GetCurrentClinicUseCase getCurrentClinicUseCase;

    @GetMapping("")
    ResponseEntity<List<SearchClinicsOutput>> searchClinics(@RequestParam(required = false)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate date,
                                                            @RequestParam(required = false) Long appointmentTypeId)  {
        return ResponseEntity.ok(searchClinicsQuery.searchClinics(date, appointmentTypeId));
    }

    @GetMapping("/{clinicId}")
    ResponseEntity<GetClinicProfileOutput> getClinicProfile(@PathVariable Long clinicId) {
        return ResponseEntity.ok(getClinicProfileQuery.getClinicProfile(clinicId));
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

    @GetMapping("/prices")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    ResponseEntity<Map<Long, BigDecimal>> getPrices() {
        return ResponseEntity.ok(mapResponse(getAppointmentPriceUseCase.getPrices()));
    }

    private Map<Long, BigDecimal> mapResponse(Map<AppointmentType, Money> map) {
        return map.
                entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getId(),
                        entry -> entry.getValue().getAmount()));
    }

    @PostMapping("/addPrice")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    ResponseEntity<Map<Long, BigDecimal>> addPrices(@
                                                            RequestBody AddPriceRequest addPriceRequest) {
        AddPriceToAppointmentTypeUseCase.AddPriceCommand addPriceCommand = createAddPriceCommand(addPriceRequest);
        addPriceToAppointmentTypeUseCase.addPrice(addPriceCommand);

        return ResponseEntity.ok(mapResponse(getAppointmentPriceUseCase.getPrices()));
    }

    @GetMapping("/getCurrent")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    ResponseEntity<GetCurrentClinicResponse> getCurrent() {
        return ResponseEntity.ok(getCurrentClinicUseCase.getCurrentClinic());
    }

    private AddPriceToAppointmentTypeUseCase.AddPriceCommand createAddPriceCommand(AddPriceRequest addPriceRequest) {
        return new AddPriceToAppointmentTypeUseCase.AddPriceCommand(
                addPriceRequest.getAppointmentTypeId(),
                addPriceRequest.getPrice()
        );
    }
}
