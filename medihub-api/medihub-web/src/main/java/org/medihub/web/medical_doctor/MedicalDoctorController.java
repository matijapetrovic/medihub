package org.medihub.web.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase.AddMedicalDoctorCommand;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsOutput;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsQuery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical-doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalDoctorController {
    private final AddMedicalDoctorUseCase AddMedicalDoctorUseCase;
    private final GetAllDoctorsPort getAllDoctorsPort;
    private final GetDoctorsQuery getDoctorsQuery;

    @GetMapping("/{clinicId}")
    ResponseEntity<List<GetDoctorsOutput>> getDoctors(@PathVariable Long clinicId) {
        return ResponseEntity.ok(getDoctorsQuery.getDoctorsForClinic(clinicId));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    void add(@RequestBody MedicalDoctorRequest request) {
        AddMedicalDoctorCommand command = createCommand(request);
        AddMedicalDoctorUseCase.addDoctor(command);
    }

    @GetMapping("/getAll")
    List<?> getAll(){
        return getAllDoctors();
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

    private List<?> getAllDoctors(){
        return getAllDoctorsPort.getAllDoctors()
                .stream()
                .map(doctor -> new MedicalDoctorResponse(
                    doctor.getAccount().getId(),
                        doctor.getAccount().getEmail(),
                        doctor.getAccount().getFirstName(),
                        doctor.getAccount().getLastName(),
                        doctor.getAccount().getPersonalInfo().getAddress(),
                        doctor.getAccount().getPersonalInfo().getTelephoneNumber(),
                        doctor.getWorkingTime().getFrom().toString(),
                        doctor.getWorkingTime().getTo().toString(),
                        doctor.getClinic().getName()
                ))
                .collect(Collectors.toList());
    }
}
