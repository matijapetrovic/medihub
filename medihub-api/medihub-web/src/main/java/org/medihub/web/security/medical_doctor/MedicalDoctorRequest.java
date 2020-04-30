package org.medihub.web.security.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medihub.domain.Appointment;
import org.medihub.domain.Clinic;
import org.medihub.domain.WorkingCalendar;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDoctorRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String country;

    private String telephoneNumber;

    private boolean passwordChanged;
}
