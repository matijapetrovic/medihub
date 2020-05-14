package org.medihub.web.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.*;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase.AddAppointmentTypeCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final DeleteAppointmentTypeUseCase deleteAppointmentTypeUseCase;

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

    @PostMapping("/delete")
    private void delete(@RequestBody Long id) {
        deleteAppointmentTypeUseCase.delete(id);
    }
}
