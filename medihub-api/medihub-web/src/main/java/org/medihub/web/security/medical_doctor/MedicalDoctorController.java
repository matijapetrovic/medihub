package org.medihub.web.security.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddDoctorUseCase;
import org.medihub.domain.MedicalDoctor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical-doctors", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalDoctorController {
    private final AddDoctorUseCase AddDoctorUseCase;

    @PostMapping("/add")
    void add(@RequestBody MedicalDoctorRequest request) {
        MedicalDoctor doctor =
                new MedicalDoctor(
                        request.getWorkingCalendar(),
                        request.getClinic(),
                        request.getAppointments());

        AddDoctorUseCase.addDoctor(doctor);
    }
}
