package org.medihub.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.clinic_room.ClinicRoom;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class PredefinedAppointment {
    private Long id;
    private Long doctorId;
    private LocalTime start;
    private double duration;
    private ClinicRoom clinicRoom;
    private AppointmentType appointmentType;
}
