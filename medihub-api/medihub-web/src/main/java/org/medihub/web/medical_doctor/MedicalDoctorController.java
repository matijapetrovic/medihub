package org.medihub.web.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddDoctorUseCase;
import org.medihub.domain.*;
import org.medihub.domain.identity.Account;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical-doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalDoctorController {
    private final AddDoctorUseCase AddDoctorUseCase;

    @PostMapping("/add")
    void add(@RequestBody MedicalDoctorRequest request) {
        MedicalDoctor doctor = new MedicalDoctor(
                        new Account(
                        null,
                        request.getEmail(),
                        request.getPassword(),
                        new PersonalInfo(
                                request.getFirstName(),
                                request.getLastName(),
                                new Address(
                                        request.getAddress(),
                                        request.getCity(),
                                        request.getCountry()
                                ),
                                request.getTelephoneNumber()
                        ),
                        request.isPasswordChanged(),
                                null
                    ),
                        new WorkingCalendar(),
                        new Clinic("clinic1", new Address("a", "london", "gb"),"desc"),
                        new HashSet<>()
                );

        AddDoctorUseCase.addDoctor(doctor);
    }
}
