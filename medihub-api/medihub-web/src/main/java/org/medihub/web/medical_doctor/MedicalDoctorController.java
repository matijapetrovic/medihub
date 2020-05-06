package org.medihub.web.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase.AddMedicalDoctorCommand;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/medical-doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalDoctorController {
    private final AddMedicalDoctorUseCase AddMedicalDoctorUseCase;

    @PostMapping("/add")
    void add(@RequestBody MedicalDoctorRequest request) {
        AddMedicalDoctorCommand command = createCommand(request);
        AddMedicalDoctorUseCase.addDoctor(command);
    }

    private AddMedicalDoctorCommand createCommand(MedicalDoctorRequest medicalDoctorRequest){
        return new AddMedicalDoctorCommand(
                null,
                medicalDoctorRequest.getEmail(),
                medicalDoctorRequest.getPassword(),
                medicalDoctorRequest.getFirstName(),
                medicalDoctorRequest.getLastName(),
                medicalDoctorRequest.getAddress(),
                medicalDoctorRequest.getCity(),
                medicalDoctorRequest.getCountry(),
                medicalDoctorRequest.getTelephoneNum(),
                medicalDoctorRequest.isPasswordChanged(),
                medicalDoctorRequest.getFrom(),
                medicalDoctorRequest.getTo(),
                medicalDoctorRequest.getAppointmentType()
        );
    }
}
