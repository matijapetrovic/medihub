package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Appointment {
    private Long id;
    private Long patientId;
    private Date date;
    private double duration;
    private ClinicRoom clinicRoom;
    private AppointmentType appointmentType;
    private Set<Prescription> prescriptions;
}
