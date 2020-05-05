package org.medihub.web.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase;
import org.medihub.application.ports.incoming.appointment_type.GetAppointmentTypesOutput;
import org.medihub.application.ports.incoming.appointment_type.GetAppointmentTypesQuery;
import org.medihub.domain.AppointmentType;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase.AddAppointmentTypeCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/appointment-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentTypeController {
    private final AddAppointmentTypeUseCase addAppointmentTypeUseCase;
    private final GetAppointmentTypesQuery getAppointmentTypesQuery;

    @PostMapping("/add")
    private void add(@RequestBody AppointmentTypeRequest appointmentTypeRequest){
        AddAppointmentTypeCommand command = createCommand(appointmentTypeRequest);

        addAppointmentTypeUseCase.addAppointmentType(command);
    }

    private AddAppointmentTypeCommand createCommand(AppointmentTypeRequest request){
        return  new AddAppointmentTypeCommand(null, request.getName());
    }

    @GetMapping("")
    ResponseEntity<List<GetAppointmentTypesOutput>> get() {
        return ResponseEntity.ok(getAppointmentTypesQuery.getAppointmentTypes());
    }
}
