package org.medihub.application.ports.incoming.appointment_type;

import lombok.Value;
import org.medihub.application.ports.incoming.medical_record.AllergyDTO;
import org.medihub.application.ports.incoming.medical_record.ChangeMedicalRecordUseCase;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ChangeAppointmentTypeUseCase {
    void changeAppointmentType(ChangeAppointmentTypeCommand command);

    @Value
    class ChangeAppointmentTypeCommand extends SelfValidating<ChangeAppointmentTypeCommand> {
        @NotNull
        Long id;
        @NotBlank
        String name;
    }
}
