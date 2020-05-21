package org.medihub.web.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase.AddPredefinedAppointmentCommand;
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
@RequestMapping(value = "/predefined-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PredefinedAppointmentController {
    private final AddPredefinedAppointmentUseCase addPredefinedAppointmentUseCase;

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
                request.getAppointmentTypeId()
        );
    }
}
