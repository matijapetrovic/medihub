package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;
import org.medihub.application.ports.incoming.medical_record.AllergyDTO;
import org.medihub.application.ports.incoming.medical_record.ChangeMedicalRecordUseCase;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ChangeFinishedAppointmentUseCase {
    void changeFinishedAppointment(ChangeFinishedAppointmentCommand command);

    @Value
    class ChangeFinishedAppointmentCommand extends SelfValidating<ChangeFinishedAppointmentCommand> {
        @NotNull
        Long id;
        @NotNull
        Long diagnosis;
        @NotBlank
        String description;
    }
}
