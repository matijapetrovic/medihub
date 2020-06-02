package org.medihub.web.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.medical_doctor.*;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase.AddMedicalDoctorCommand;
import org.medihub.application.ports.incoming.medical_doctor.schedule.GetDoctorScheduleOutput;
import org.medihub.application.ports.incoming.medical_doctor.schedule.GetDoctorScheduleQuery;
import org.medihub.application.ports.incoming.scheduling.GetDoctorAvailableTimesQuery;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical-doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalDoctorController {
    private final AddMedicalDoctorUseCase AddMedicalDoctorUseCase;
    private final GetMedicalDoctorUseCase getMedicalDoctorUseCase;
    private final GetDoctorsQuery getDoctorsQuery;
    private final SearchDoctorsQuery searchDoctorsQuery;
    private final GetDoctorAvailableTimesQuery getDoctorAvailableTimesQuery;
    private final GetDoctorScheduleQuery getDoctorScheduleQuery;
    private final DeleteMedicalDoctorUseCase deleteMedicalDoctorUseCase;

    @GetMapping("/{clinicId}")
    ResponseEntity<List<SearchDoctorsOutput>> getDoctors(@PathVariable Long clinicId) {
        return ResponseEntity.ok(getDoctorsQuery.getDoctorsForClinic(clinicId));
    }

    @GetMapping("/{doctorId}/available_times/{date}")
    ResponseEntity<List<String>> getAvailableTimes(@PathVariable Long doctorId,
                                                      @PathVariable
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate date) {
        return ResponseEntity.ok(getDoctorAvailableTimesQuery.getAvailableTimes(doctorId, date));
    }

    @GetMapping("")
    ResponseEntity<List<SearchDoctorsOutput>> searchDoctors(@RequestParam Long clinicId,
                                                            @RequestParam
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate date,
                                                            @RequestParam Long appointmentTypeId) {
        return ResponseEntity.ok(searchDoctorsQuery.searchDoctors(clinicId, date, appointmentTypeId));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody MedicalDoctorRequest request) {
        AddMedicalDoctorCommand command = createCommand(request);
        AddMedicalDoctorUseCase.addDoctor(command);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void delete(@RequestBody Long id) throws ForbiddenException {
        deleteMedicalDoctorUseCase.deleteMedicalDoctor(id);
    }

    @GetMapping("/getAll")
    List<?> getAll(){
        return getMedicalDoctorUseCase.loadAll();
    }

    private AddMedicalDoctorCommand createCommand(MedicalDoctorRequest medicalDoctorRequest) {
        return new AddMedicalDoctorCommand(
                medicalDoctorRequest.getEmail(),
                medicalDoctorRequest.getPassword(),
                medicalDoctorRequest.getFirstName(),
                medicalDoctorRequest.getLastName(),
                medicalDoctorRequest.getAddress(),
                medicalDoctorRequest.getCity(),
                medicalDoctorRequest.getCountry(),
                medicalDoctorRequest.getTelephoneNum(),
                medicalDoctorRequest.getFrom(),
                medicalDoctorRequest.getTo(),
                medicalDoctorRequest.getAppointmentTypeId()
        );
    }

    @GetMapping("/schedule")
    ResponseEntity<GetDoctorScheduleOutput> getSchedules() {
        return ResponseEntity.ok(getDoctorScheduleQuery.getDoctorSchedule());
    }

    @GetMapping("/schedule/{id}")
    ResponseEntity<GetDoctorScheduleOutput> getSchedulesByDoctorId(@PathVariable Long id) {
        return ResponseEntity.ok(getDoctorScheduleQuery.getDoctorSchedule());
    }
}
