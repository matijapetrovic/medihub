package org.medihub.web.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.AddFinishedAppointmentUseCase;
import org.medihub.application.ports.incoming.finished_appointment.FinishedAppointmentOutput;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.web.appointment.AddAppointmentRequest;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/finished_appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class FinishedAppointmentController {
    private final AddFinishedAppointmentUseCase addFinishedAppointmentUseCase;

    @PostMapping("/add")
    ResponseEntity<FinishedAppointmentOutput> addFinishedAppointment(@RequestBody AddFinishedAppointmentRequest request) {
        AddFinishedAppointmentUseCase.AddFinishedAppointmentCommand command = this.createCommand(request);
        return ResponseEntity.ok(addFinishedAppointmentUseCase.addFinishedAppointment(command));
    }

    private AddFinishedAppointmentUseCase.AddFinishedAppointmentCommand createCommand(AddFinishedAppointmentRequest request) {
        return new AddFinishedAppointmentUseCase.AddFinishedAppointmentCommand(
                request.getItemId(),
                request.getDescription(),
                request.getAppointment(),
                request.getDrugs(),
                request.getDiagnosis()
        );
    }
}
