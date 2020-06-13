package org.medihub.web.medical_nurse;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.schedule.GetScheduleOutput;
import org.medihub.application.ports.incoming.medical_nurse.GetNurseScheduleQuery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical-nurse", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalNurseController {
    private final GetNurseScheduleQuery getNurseScheduleQuery;

    @GetMapping("/schedule")
    public ResponseEntity<GetScheduleOutput> getSchedule(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok(getNurseScheduleQuery.getSchedule(id));
    }
}
