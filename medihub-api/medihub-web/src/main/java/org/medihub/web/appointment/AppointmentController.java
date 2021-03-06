package org.medihub.web.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase.AddAppointmentCommand;
import org.medihub.application.ports.incoming.appointment.CancelAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.GetAppointmentsOutput;
import org.medihub.application.ports.incoming.appointment.GetAppointmentsQuery;
import org.medihub.application.ports.incoming.appointment.GetCurrentAppointmentUseCase;
import org.medihub.domain.appointment.Appointment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.NotActiveException;
import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
    private final AddAppointmentUseCase addAppointmentUseCase;
    private final GetAppointmentsQuery getAppointmentsQuery;
    private final CancelAppointmentUseCase cancelAppointmentUseCase;
    private final GetCurrentAppointmentUseCase getCurrentAppointmentUseCase;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<GetAppointmentsOutput>> get() {
        return ResponseEntity.ok(getAppointmentsQuery.getAppointments());
    }

    @PostMapping("/{appointmentId}/cancel")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public void cancel(@PathVariable Long appointmentId) throws ForbiddenException, NotFoundException {
        cancelAppointmentUseCase.cancelAppointment(appointmentId);
    }

    @GetMapping("/getCurrent/{patientId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public ResponseEntity<Appointment> getCurrent(@PathVariable Long patientId) {
        return ResponseEntity.ok(getCurrentAppointmentUseCase.getCurrentAppointment(patientId));
    }

}
