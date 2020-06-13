package org.medihub.application.ports.incoming.medical_doctor.schedule;

import lombok.AllArgsConstructor;
import org.medihub.domain.appointment.Appointment;

@AllArgsConstructor
class ClinicRoomOutput {
   public Long id;
   public String name;
   public int number;
}

@AllArgsConstructor
class DoctorOutput {
    public Long id;
    public String firstName;
    public String secondName;
}

@AllArgsConstructor
class PatientOutput {
    public Long id;
    public String firstName;
    public String lastName;
}

@AllArgsConstructor
class AppointmentOutput {
    public long id;
    public String date;
    public String time;
    public PatientOutput patient;
    public DoctorOutput doctor;
    public ClinicRoomOutput clinicRoom;
}

public class AppointmentScheduleItemOutput extends DailyScheduleItemOutput {
    public AppointmentOutput appointment;

    public AppointmentScheduleItemOutput(Long id, String time, String type, Appointment appointment) {
        super(id, time, type);
        this.appointment = new AppointmentOutput(
                appointment.getId(),
                appointment.getDate().toString(),
                appointment.getTime().toString(),
                new PatientOutput(appointment.getPatient().getId(),
                        appointment.getPatient().getPersonalInfo().getFirstName(),
                        appointment.getPatient().getPersonalInfo().getLastName()),
                new DoctorOutput(appointment.getDoctor().getId(),
                        appointment.getDoctor().getPersonalInfo().getFirstName(),
                        appointment.getDoctor().getPersonalInfo().getLastName()),
                new ClinicRoomOutput(appointment.getClinicRoom().getId(),
                        appointment.getClinicRoom().getName(),
                        appointment.getClinicRoom().getNumber())
        );
    }
}
