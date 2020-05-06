package org.medihub.web.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase.AddMedicalDoctorCommand;
import org.medihub.application.ports.outgoing.doctor.GetDoctorPort;
import org.medihub.domain.MedicalDoctor;
import org.springframework.http.MediaType;
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
    private final GetDoctorPort getDoctorPort;

    @PostMapping("/add")
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
                null,
                medicalDoctorRequest.getEmail(),
                medicalDoctorRequest.getPassword(),
                medicalDoctorRequest.getFirstName(),
                medicalDoctorRequest.getLastName(),
                medicalDoctorRequest.getAddress(),
                medicalDoctorRequest.getCity(),
                medicalDoctorRequest.getCountry(),
                medicalDoctorRequest.getTelephoneNumber(),
                medicalDoctorRequest.isPasswordChanged(),
                medicalDoctorRequest.getFrom(),
                medicalDoctorRequest.getTo(),
                medicalDoctorRequest.getAppointmentType()
        );
    }

    private List<?> getAllDoctors(){
        return getDoctorPort.getAllDoctors()
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
