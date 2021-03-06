package org.medihub.domain.medical_doctor;

import lombok.Getter;
import lombok.Setter;
import org.medihub.domain.appointment.Appointment;

import java.time.LocalTime;

@Getter
@Setter
public class MedicalDoctorAppointmentScheduleItem extends MedicalDoctorScheduleItem {
    private Appointment appointment;

    public MedicalDoctorAppointmentScheduleItem(
            Long id,
            LocalTime time,
            MedicalDoctorScheduleItemType type,
            Appointment appointment) {
        super(id, time, type);
        this.appointment = appointment;
    }
}
