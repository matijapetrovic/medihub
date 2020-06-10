package org.medihub.web.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase.AddAppointmentCommand;
import org.medihub.application.ports.incoming.appointment.GetAppointmentsOutput;
import org.medihub.application.ports.incoming.appointment.GetAppointmentsQuery;
import org.medihub.application.ports.incoming.appointment.GetCurrentAppointmentUseCase;
import org.medihub.domain.appointment.Appointment;
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
    private final GetCurrentAppointmentUseCase getCurrentAppointmentUseCase;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    ResponseEntity<List<GetAppointmentsOutput>> get() {
        return ResponseEntity.ok(getAppointmentsQuery.getAppointments());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody AddAppointmentRequest request) {
        AddAppointmentCommand command = createCommand(request);
        addAppointmentUseCase.addAppointment(command);
    }

    @GetMapping("/getCurrent/{patientId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    ResponseEntity<Appointment> getCurrent(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(getCurrentAppointmentUseCase.getCurrentAppointment(patientId));
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
