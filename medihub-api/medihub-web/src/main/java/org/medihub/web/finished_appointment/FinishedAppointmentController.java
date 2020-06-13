package org.medihub.web.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.finished_appointment.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import org.medihub.application.ports.incoming.finished_appointment.AddFinishedAppointmentUseCase.AddFinishedAppointmentCommand;
import org.medihub.application.ports.incoming.finished_appointment.GetFinishedAppointmentProfitUseCase.GetFinishedAppointmentProfitCommand;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/finished_appointment", produces = MediaType.APPLICATION_JSON_VALUE)
public class FinishedAppointmentController {
    private final GetAppointmentHistoryQuery getAppointmentHistoryQuery;
    private final AddFinishedAppointmentUseCase addFinishedAppointmentUseCase;
    private final GetFinishedAppointmentProfitUseCase getFinishedAppointmentProfitUseCase;
    private final GetPatientsFinishedAppointmetsQuery getPatientsFinishedAppointmetsQuery;
    private final ChangeFinishedAppointmentUseCase changeFinishedAppointmentUseCase;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<GetAppointmentHistoryOutput>> getAppointmentHistory() {
        return ResponseEntity.ok(getAppointmentHistoryQuery.getAppointmentHistory());
    }

    @PostMapping("/getProfit")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public ResponseEntity<FinishedAppointmentProfitResponse> getProfit(
            @RequestBody GetProfitRequest getProfitRequest
    ) {
        GetFinishedAppointmentProfitCommand command = makeProfitCommand(getProfitRequest);
        return ResponseEntity.ok(getFinishedAppointmentProfitUseCase.getProfit(command));
    }

    private GetFinishedAppointmentProfitCommand makeProfitCommand(GetProfitRequest getProfitRequest) {
        return new GetFinishedAppointmentProfitCommand(
                LocalDate.parse(getProfitRequest.getFrom()),
                LocalDate.parse(getProfitRequest.getTo())
        );
    }

    @PostMapping("/add")
    public ResponseEntity<GetFinishedAppointmentOutput> add(@RequestBody AddFinishedAppointmentRequest request) throws NotFoundException {
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

    @GetMapping("/{patientId}")
    public ResponseEntity<List<GetFinishedAppointmentOutput>> getPatientsFinishedAppointments(@PathVariable Long patientId) {
        return ResponseEntity.ok(getPatientsFinishedAppointmetsQuery.getPatientsFinishedAppointments(patientId));
    }

    @PostMapping("/change")
    public void changeFinishedAppointment(@RequestBody ChangeFinishedAppointmentRequest request) {
        ChangeFinishedAppointmentUseCase.ChangeFinishedAppointmentCommand command = createChangeCommand(request);
        changeFinishedAppointmentUseCase.changeFinishedAppointment(command);
    }

    ChangeFinishedAppointmentUseCase.ChangeFinishedAppointmentCommand createChangeCommand(ChangeFinishedAppointmentRequest request) {
        return new ChangeFinishedAppointmentUseCase.ChangeFinishedAppointmentCommand(
                request.getId(),
                request.getDiagnosis(),
                request.getDescription()
        );
    }
}
