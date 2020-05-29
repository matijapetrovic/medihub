package org.medihub.web.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.medihub.application.ports.incoming.finished_appointment.AddFinishedAppointmentUseCase.AddFinishedAppointmentCommand;

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

    @PostMapping("/getForClinic")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    ResponseEntity<List<GetAppointmentDateCount>> getClinicAppointmentHistory (
            @RequestBody FinishedAppointmentType finishedAppointmentType) {
        GetAppointmentHistoryQuery.FinishedAppointmentQuery query = makeFinishedAppointmentQuery(finishedAppointmentType);
        return ResponseEntity.ok(getAppointmentHistoryQuery.getClinicAppointmentHistory(query));
    }

    private GetAppointmentHistoryQuery.FinishedAppointmentQuery makeFinishedAppointmentQuery(
            FinishedAppointmentType finishedAppointmentType) {
        return new GetAppointmentHistoryQuery.FinishedAppointmentQuery(
                finishedAppointmentType.getType(),
                finishedAppointmentType.getDate());
    }

    @PostMapping("/add")
    ResponseEntity<GetFinishedAppointmentOutput> add(@RequestBody AddFinishedAppointmentRequest request) {
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
