package org.medihub.web.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.medical_doctor.*;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase.AddMedicalDoctorCommand;
import org.medihub.application.ports.incoming.medical_doctor.schedule.GetScheduleOutput;
import org.medihub.application.ports.incoming.medical_doctor.schedule.GetDoctorScheduleQuery;
import org.medihub.application.ports.incoming.scheduling.GetAppointmentScheduleItemByAppointmentIdUseCase;
import org.medihub.application.ports.incoming.scheduling.GetDoctorAvailableTimesQuery;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.application.ports.incoming.patient.PatientResponse;
import org.medihub.domain.medical_doctor.MedicalDoctor;
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
    private final GetAllClinicMedicalDoctorsUseCase getMedicalDoctorUseCase;
    private final GetDoctorsQuery getDoctorsQuery;
    private final SearchDoctorsQuery searchDoctorsQuery;
    private final GetDoctorAvailableTimesQuery getDoctorAvailableTimesQuery;
    private final GetDoctorScheduleQuery getDoctorScheduleQuery;
    private final GetAppointmentScheduleItemByAppointmentIdUseCase getAppointmentScheduleItemByAppointmentIdUseCase;
    private final GetPreviousPatientsQuery getPreviousPatientsQuery;
    private final DeleteDoctorUseCase deleteDoctorUseCase;
    private final GetMedicalDoctorByClinicIdOnDateUseCase getMedicalDoctorByClinicIdOnDateUseCase;

    @PostMapping("/delete/{doctorId}")
    public void deleteDoctor(@PathVariable Long doctorId) throws ForbiddenException {
        deleteDoctorUseCase.deleteDoctor(doctorId);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<List<GetDoctorsOutput>> getDoctors(@PathVariable Long clinicId) {
        return ResponseEntity.ok(getDoctorsQuery.getDoctorsForClinic(clinicId));
    }

    @GetMapping("/getScheduleItem/{id}")
    public ResponseEntity<MedicalDoctorAppointmentScheduleItem> getAppointmentScheduleItem(
            @PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(
                getAppointmentScheduleItemByAppointmentIdUseCase.getItem(id));
    }

    @GetMapping("/{doctorId}/available_times/{date}")
    public ResponseEntity<List<String>> getAvailableTimes(@PathVariable Long doctorId,
                                                      @PathVariable
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate date) {
        return ResponseEntity.ok(getDoctorAvailableTimesQuery.getAvailableTimes(doctorId, date));
    }

    @GetMapping("")
    public ResponseEntity<List<SearchDoctorsOutput>> searchDoctors(@RequestParam Long clinicId,
                                                            @RequestParam(required = false)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate date,
                                                            @RequestParam(required = false)
                                                                    Long appointmentTypeId) {
        return ResponseEntity.ok(searchDoctorsQuery.searchDoctors(clinicId, date, appointmentTypeId));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public void add(@RequestBody MedicalDoctorRequest request) {
        AddMedicalDoctorCommand command = createCommand(request);
        AddMedicalDoctorUseCase.addDoctor(command);
    }

    @GetMapping("/getAll")
    public List<MedicalDoctorResponse> getAll() {
        return getAllDoctors(getMedicalDoctorUseCase.loadAll());
    }

    @GetMapping("/getAllOnDate/{date}")
    public List<MedicalDoctorResponse> getAllOnDate(@PathVariable String date){
        return getAllDoctors(getMedicalDoctorByClinicIdOnDateUseCase.loadAll(LocalDate.parse(date)));
    }

    private AddMedicalDoctorCommand createCommand(MedicalDoctorRequest medicalDoctorRequest){
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

    private List<MedicalDoctorResponse> getAllDoctors(List<MedicalDoctor> doctors) {
        return doctors
                .stream()
                .map(doctor -> new MedicalDoctorResponse(
                        doctor.getId(),
                        doctor.getPersonalInfo().getAccount().getEmail(),
                        doctor.getPersonalInfo().getFirstName(),
                        doctor.getPersonalInfo().getLastName(),
                        doctor.getPersonalInfo().getAddress(),
                        doctor.getPersonalInfo().getTelephoneNumber(),
                        doctor.getWorkingTime().getFrom().toString(),
                        doctor.getWorkingTime().getTo().toString(),
                        doctor.getClinic().getName(),
                        doctor.getSpecialization().getName(),
                        doctor.getSpecialization().getId(),
                        doctor.getRating().getRating(),
                        doctor.getRating().getCount()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/schedule")
    public ResponseEntity<GetScheduleOutput> getSchedules() {
        return ResponseEntity.ok(getDoctorScheduleQuery.getDoctorSchedule());
    }

    @GetMapping("/schedule/:{id}")
    public ResponseEntity<GetScheduleOutput> getSchedulesByDoctorId(@PathVariable Long id) {
        return ResponseEntity.ok(getDoctorScheduleQuery.getDoctorSchedule(id));
    }

    
    @GetMapping("/previous-patients")
    public ResponseEntity<List<PatientResponse>> getPreviousPatients() {
        return ResponseEntity.ok(getPreviousPatientsQuery.getPreviousPatients());
    }
}
