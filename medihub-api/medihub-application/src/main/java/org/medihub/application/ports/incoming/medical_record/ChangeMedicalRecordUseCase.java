package org.medihub.application.ports.incoming.medical_record;

import lombok.Value;
import org.medihub.application.ports.incoming.medical_record.dto.AllergyDTO;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ChangeMedicalRecordUseCase {
    void changeMedicalRecord(ChangeMedicalRecordCommand command);

    @Value
    class ChangeMedicalRecordCommand extends SelfValidating<ChangeMedicalRecordCommand> {
        @NotNull
        Long id;
        @NotNull
        Integer height;
        @NotNull
        Integer weight;
        @NotBlank
        String bloodType;
        @NotNull
        Boolean rhPositive;
        @NotNull
        Double leftDioptry;
        @NotNull
        Double rightDioptry;
        @NotNull
        List<AllergyDTO> allergies;
    }
}
