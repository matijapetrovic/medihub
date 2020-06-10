package org.medihub.web.reports;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentDateCount;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery;
import org.medihub.web.finished_appointment.FinishedAppointmentType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportsController {
    private final GetAppointmentHistoryQuery getAppointmentHistoryQuery;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public ResponseEntity<List<GetAppointmentDateCount>>
    get(
            @RequestParam String type,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        FinishedAppointmentType finishedAppointmentType = new FinishedAppointmentType(type, date);
        GetAppointmentHistoryQuery.FinishedAppointmentQuery query = makeFinishedAppointmentQuery(finishedAppointmentType);
        return ResponseEntity.ok(getAppointmentHistoryQuery.getClinicAppointmentHistory(query));
    }

    private GetAppointmentHistoryQuery.FinishedAppointmentQuery makeFinishedAppointmentQuery(
            FinishedAppointmentType finishedAppointmentType) {
        return new GetAppointmentHistoryQuery.FinishedAppointmentQuery(
                finishedAppointmentType.getType(),
                finishedAppointmentType.getDate());
    }

}
