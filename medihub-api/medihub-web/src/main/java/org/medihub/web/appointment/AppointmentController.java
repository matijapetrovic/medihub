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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
    private final AddAppointmentUseCase addAppointmentUseCase;
    private final GetAppointmentsQuery getAppointmentsQuery;
    private final CancelAppointmentUseCase cancelAppointmentUseCase;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<GetAppointmentsOutput>> get() {
        return ResponseEntity.ok(getAppointmentsQuery.getAppointments());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public void add(@RequestBody AddAppointmentRequest request) throws NotFoundException, NotAvailableException {
        AddAppointmentCommand command = createCommand(request);
        addAppointmentUseCase.addAppointment(command);
    }

    @PostMapping("/{appointmentId}/cancel")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public void cancel(@PathVariable Long appointmentId) throws ForbiddenException {
        cancelAppointmentUseCase.cancelAppointment(appointmentId);
    }

    private AddAppointmentCommand createCommand(AddAppointmentRequest addAppointmentRequest) {
        return new AddAppointmentCommand(
                addAppointmentRequest.getDate(),
                addAppointmentRequest.getTime(),
                addAppointmentRequest.getPatientId(),
                addAppointmentRequest.getDoctorId(),
                addAppointmentRequest.getClinicRoomId()
        );
    }

}
