package org.medihub.web.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medihub.domain.identity.Authority;

import java.util.List;

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
    private String telephoneNum;
    private boolean passwordChanged;
    private String from;
    private String to;
    private String appointmentType;
}
