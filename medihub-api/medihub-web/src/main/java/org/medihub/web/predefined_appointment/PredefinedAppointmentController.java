package org.medihub.web.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase.AddPredefinedAppointmentCommand;
import org.medihub.application.ports.incoming.predefined_appointment.GetPredefinedAppointmentsOutput;
import org.medihub.application.ports.incoming.predefined_appointment.GetPredefinedAppointmentsQuery;
import org.medihub.application.ports.incoming.scheduling.SchedulePredefinedAppointmentUseCase;
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
    private final SchedulePredefinedAppointmentUseCase schedulePredefinedAppointmentUseCase;

    @GetMapping("")
    ResponseEntity<List<GetPredefinedAppointmentsOutput>> get(@RequestParam Long clinicId) {
        return ResponseEntity.ok(getPredefinedAppointmentsQuery.getPredefinedAppointments(clinicId));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody PredefinedAppointmentRequest request) throws NotAvailableException {
        AddPredefinedAppointmentCommand command = createCommand(request);
        addPredefinedAppointmentUseCase.addPredefinedAppointment(command);
    }

    @PostMapping("/{appointmentId}/schedule")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    void schedule(@PathVariable Long appointmentId) throws NotAvailableException, NotFoundException {
        schedulePredefinedAppointmentUseCase.schedulePredefinedAppointment(appointmentId);
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
