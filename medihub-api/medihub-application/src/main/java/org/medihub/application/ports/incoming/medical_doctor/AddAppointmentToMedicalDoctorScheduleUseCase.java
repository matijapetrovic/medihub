package org.medihub.application.ports.incoming.medical_doctor;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase.AddAppointmentCommand;
import org.medihub.common.SelfValidating;
import org.medihub.domain.appointment.Appointment;

import javax.validation.constraints.NotNull;

public interface AddAppointmentToMedicalDoctorScheduleUseCase {
    void addAppointmentToMedicalDoctorSchedule(AddAppointmentToScheduleCommand command, Appointment appointment);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddAppointmentToScheduleCommand extends SelfValidating<AddAppointmentToMedicalDoctorScheduleUseCase.AddAppointmentToScheduleCommand> {

        @NotNull
        String date;
        @NotNull
        String time;

        public AddAppointmentToScheduleCommand(
                String date,
                String time) {
            this.date = date;
            this.time = time;
        }
    }

}
