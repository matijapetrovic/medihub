package org.medihub.domain.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class Patient  extends  User{
    private MedicalRecord medicalRecord;
    private Set<Appointment> apointments;
}
