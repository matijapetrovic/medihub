package org.medihub.web.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase.AddAppointmentCommand;
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
@RequestMapping(value = "/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentController {
    private final AddAppointmentUseCase addAppointmentUseCase;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody AddAppointmentRequest request) {
        AddAppointmentCommand command = createCommand(request);
        addAppointmentUseCase.addAppointment(command);
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
