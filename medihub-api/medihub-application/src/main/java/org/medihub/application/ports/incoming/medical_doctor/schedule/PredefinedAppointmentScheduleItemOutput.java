package org.medihub.application.ports.incoming.medical_doctor.schedule;

import lombok.AllArgsConstructor;
import org.medihub.domain.appointment.PredefinedAppointment;

import java.math.BigDecimal;

@AllArgsConstructor
class AppointmentTypeOutput {
    public Long id;
    public String name;
}

@AllArgsConstructor
class PredefinedAppointmentOutput {
    public long id;
    public String date;
    public String time;
    public DoctorOutput doctor;
    public ClinicRoomOutput clinicRoom;
    public double duration;
    public BigDecimal price;
    public AppointmentTypeOutput appointmentType;
}

public class PredefinedAppointmentScheduleItemOutput extends DailyScheduleItemOutput{
    public PredefinedAppointmentOutput predefinedAppointmentOutput;

    public PredefinedAppointmentScheduleItemOutput(
            Long id, String time, String type, PredefinedAppointment predefinedAppointment) {
        super(id, time, type);
        this.predefinedAppointmentOutput = new PredefinedAppointmentOutput(
                predefinedAppointment.getId(),
                predefinedAppointment.getDate().toString(),
                predefinedAppointment.getStart().toString(),
                new DoctorOutput(
                        predefinedAppointment.getDoctor().getId(),
                        predefinedAppointment.getDoctor().getFirstName(),
                        predefinedAppointment.getDoctor().getLastName()
                ),
                new ClinicRoomOutput(
                        predefinedAppointment.getClinicRoom().getId(),
                        predefinedAppointment.getClinicRoom().getName(),
                        predefinedAppointment.getClinicRoom().getNumber()),
                predefinedAppointment.getDuration(),
                predefinedAppointment.getPrice(),
                new AppointmentTypeOutput(
                        predefinedAppointment.getAppointmentType().getId(),
                        predefinedAppointment.getAppointmentType().getName()
                )
        );
    }
}
