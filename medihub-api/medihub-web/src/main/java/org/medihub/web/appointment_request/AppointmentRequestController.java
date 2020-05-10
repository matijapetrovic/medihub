package org.medihub.web.appointment_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.ScheduleAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment_request.ScheduleAppointmentUseCase.ScheduleAppointmentCommand;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/appointment-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentRequestController {
    private final ScheduleAppointmentUseCase scheduleAppointmentUseCase;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    void schedule(@RequestBody ScheduleAppointmentRequest request) {
        ScheduleAppointmentCommand command = createCommand(request);
        scheduleAppointmentUseCase.scheduleAppointment(command);
    }

    private ScheduleAppointmentCommand createCommand(ScheduleAppointmentRequest request) {
        return new ScheduleAppointmentCommand(
                request.getDoctorId(),
                request.getDate(),
                request.getTime());
    }
}
