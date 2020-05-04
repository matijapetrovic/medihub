package org.medihub.web.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase;
import org.medihub.domain.AppointmentType;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase.AddAppointmentTypeCommand;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/appointment-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentTypeController {
    private final AddAppointmentTypeUseCase addAppointmentTypeUseCase;

    @PostMapping("/add")
    private void add(@RequestBody AppointmentTypeRequest appointmentTypeRequest){
        AddAppointmentTypeCommand command = createCommand(appointmentTypeRequest);

        addAppointmentTypeUseCase.addAppointmentType(command);
    }

    private AddAppointmentTypeCommand createCommand(AppointmentTypeRequest request){
        return  new AddAppointmentTypeCommand(null, request.getName());
    }
}
