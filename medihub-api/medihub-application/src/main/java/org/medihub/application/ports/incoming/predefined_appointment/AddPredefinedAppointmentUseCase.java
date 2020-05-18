package org.medihub.application.ports.incoming.predefined_appointment;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface AddPredefinedAppointmentUseCase {
    void addPredefinedAppointment(AddPredefinedAppointmentUseCase.AddPredefinedAppointmentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddPredefinedAppointmentCommand extends SelfValidating<AddPredefinedAppointmentUseCase.AddPredefinedAppointmentCommand> {
        @Email
        Long doctorId;
        @Password
        String start;
        @NotBlank
        double duration;
        @NotBlank
        Long clinicRoomId;
        @NotBlank
        Long appointmentTypeId;

        public AddPredefinedAppointmentCommand(
                Long doctorId,
                String start,
                double duration,
                Long clinicRoomId,
                Long appointmentTypeId){
            this.doctorId = doctorId;
            this.start = start;
            this.duration = duration;
            this.clinicRoomId = clinicRoomId;
            this.appointmentTypeId = appointmentTypeId;
        }
    }
}
