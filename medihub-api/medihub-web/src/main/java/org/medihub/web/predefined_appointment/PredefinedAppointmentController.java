package org.medihub.web.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase.AddPredefinedAppointmentCommand;
import org.medihub.application.ports.incoming.predefined_appointment.GetPredefinedAppointmentsQuery;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/predefined-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PredefinedAppointmentController {
    private final AddPredefinedAppointmentUseCase addPredefinedAppointmentUseCase;
    private final GetPredefinedAppointmentsQuery getPredefinedAppointmentsQuery;

    @GetMapping("")
    ResponseEntity<List<PredefinedAppointment>> get(@RequestParam Long clinicId) {
        return ResponseEntity.ok(getPredefinedAppointmentsQuery.getPredefinedAppointments(clinicId));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody PredefinedAppointmentRequest request) {
        AddPredefinedAppointmentCommand command = createCommand(request);
        addPredefinedAppointmentUseCase.addPredefinedAppointment(command);
    }

    private AddPredefinedAppointmentCommand createCommand(PredefinedAppointmentRequest request) {
        return new AddPredefinedAppointmentCommand(
                request.getDoctorId(),
                request.getStart(),
                request.getDuration(),
                request.getClinicRoomId(),
                request.getAppointmentTypeId(),
                LocalDate.parse(request.getDate()),
                request.getPrice()
        );
    }
}
