package org.medihub.domain.medical_doctor;

import lombok.Getter;
import lombok.Setter;
import org.medihub.domain.appointment.PredefinedAppointment;

import java.time.LocalTime;

@Getter
@Setter
public class MedicalDoctorPredefinedAppointmentScheduleItem extends MedicalDoctorScheduleItem{
    private PredefinedAppointment predefinedAppointment;

    public MedicalDoctorPredefinedAppointmentScheduleItem(
            Long id,
            LocalTime time,
            MedicalDoctorScheduleItemType type,
            PredefinedAppointment predefinedAppointment) {
        super(id, time, type);
        this.predefinedAppointment = predefinedAppointment;
    }
}
