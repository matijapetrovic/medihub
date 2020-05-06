package org.medihub.web.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase.AddMedicalDoctorCommand;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsOutput;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsQuery;
import org.medihub.domain.*;
import org.medihub.domain.identity.Account;
import org.medihub.domain.identity.Authority;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical-doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalDoctorController {
    private final AddMedicalDoctorUseCase AddMedicalDoctorUseCase;
    private final GetDoctorsQuery getDoctorsQuery;

    @GetMapping("")
    ResponseEntity<List<GetDoctorsOutput>> getDoctors(@RequestParam Long clinicId) {
        return ResponseEntity.ok(getDoctorsQuery.getDoctorsForClinic(clinicId));
    }

    @PostMapping("/add")
    void add(@RequestBody MedicalDoctorRequest request) {
        AddMedicalDoctorCommand command = createCommand(request);
        AddMedicalDoctorUseCase.addDoctor(command);
    }

    private AddMedicalDoctorCommand createCommand(MedicalDoctorRequest medicalDoctorRequest){
        return new AddMedicalDoctorCommand(
                null,
                new Account(
                        null,
                        medicalDoctorRequest.getEmail(),
                        medicalDoctorRequest.getPassword(),
                        new PersonalInfo(
                                medicalDoctorRequest.getFirstName(),
                                medicalDoctorRequest.getLastName(),
                                new Address(
                                        medicalDoctorRequest.getAddress(),
                                        medicalDoctorRequest.getCity(),
                                        medicalDoctorRequest.getCountry()
                                ),
                                medicalDoctorRequest.getTelephoneNumber()
                        ),
                        medicalDoctorRequest.isPasswordChanged(),
                        List.of(new Authority(2L, "ROLE_DOCTOR"))
                ),
                new WorkingCalendar(null),
                new Clinic("clinic1", new Address("a", "london", "gb"),"desc"),
                new HashSet<>()
        );
    }
}
