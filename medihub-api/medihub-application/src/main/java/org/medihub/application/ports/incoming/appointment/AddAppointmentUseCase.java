package org.medihub.application.ports.incoming.appointment;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

public interface AddAppointmentUseCase {
    void addAppointment(AddAppointmentCommand addAppointmentCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddAppointmentCommand extends SelfValidating<AddAppointmentUseCase.AddAppointmentCommand> {

        String date;
        String time;
        Long patientId;
        Long doctorId;
        Long clinicRoomId;

        public AddAppointmentCommand(
                String date,
                String time,
                Long patientId,
                Long doctorId,
                Long clinicRoomId) {
            this.date = date;
            this.time = time;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.clinicRoomId = clinicRoomId;
        }
    }
}
