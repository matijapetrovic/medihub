package org.medihub.application.ports.incoming.predefined_appointment;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface AddPredefinedAppointmentUseCase {
    void addPredefinedAppointment(AddPredefinedAppointmentUseCase.AddPredefinedAppointmentCommand command) throws NotAvailableException;

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
        @NotNull
        LocalDate date;
        @NotNull
        BigDecimal price;
        @NotNull
        BigDecimal discount;

        public AddPredefinedAppointmentCommand(
                Long doctorId,
                String start,
                double duration,
                Long clinicRoomId,
                Long appointmentTypeId,
                LocalDate date,
                BigDecimal price,
                BigDecimal discount){
            this.doctorId = doctorId;
            this.start = start;
            this.duration = duration;
            this.clinicRoomId = clinicRoomId;
            this.appointmentTypeId = appointmentTypeId;
            this.date = date;
            this.price = price;
            this.discount = discount;
        }
    }
}
