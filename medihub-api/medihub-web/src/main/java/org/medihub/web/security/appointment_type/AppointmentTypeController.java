package org.medihub.web.security.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddAppointmentTypeUseCase;
import org.medihub.domain.AppointmentType;
import org.medihub.web.security.medical_doctor.MedicalDoctorRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/appointment-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentTypeController {
    private final AddAppointmentTypeUseCase addAppointmentTypeUseCase;

    @PostMapping("/add")
    private void  add(@RequestBody AppointmentTypeRequest appointmentTypeRequest){

        addAppointmentTypeUseCase.addAppointmentType(
                new AppointmentType(appointmentTypeRequest.getPrice()));
    }
}
