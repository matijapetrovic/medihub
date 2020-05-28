package org.medihub.web.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryOutput;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.medihub.application.ports.incoming.finished_appointment.AddFinishedAppointmentUseCase;
import org.medihub.application.ports.incoming.finished_appointment.AddFinishedAppointmentUseCase.AddFinishedAppointmentCommand;
import org.medihub.application.ports.incoming.finished_appointment.FinishedAppointmentOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/finished_appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class FinishedAppointmentController {
    private final GetAppointmentHistoryQuery getAppointmentHistoryQuery;
    private final AddFinishedAppointmentUseCase addFinishedAppointmentUseCase;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    ResponseEntity<List<GetAppointmentHistoryOutput>> getAppointmentHistory() {
        return ResponseEntity.ok(getAppointmentHistoryQuery.getAppointmentHistory());
    }

    @PostMapping("/add")
    ResponseEntity<FinishedAppointmentOutput> add(@RequestBody AddFinishedAppointmentRequest request) {
        AddFinishedAppointmentCommand command = this.createCommand(request);
        return ResponseEntity.ok(addFinishedAppointmentUseCase.addFinishedAppointment(command));
    }

    private AddFinishedAppointmentCommand createCommand(AddFinishedAppointmentRequest request) {
        return new AddFinishedAppointmentUseCase.AddFinishedAppointmentCommand(
                request.getItemId(),
                request.getDescription(),
                request.getAppointment(),
                request.getDrugs(),
                request.getDiagnosis()
        );
    }
}
