package org.medihub.application.ports.incoming.medical_record;

import lombok.Value;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.medical_record.Allergy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class GetMedicalRecordOutput {
    Long id;
    Integer height;
    Integer weight;
    String bloodType;
    Boolean rhPositive;
    Double leftDioptry;
    Double rightDioptry;
    List<AllergyDTO> allergies;
    List<DiagnosisDTO> diagnosis;

    public static List<AllergyDTO> mapToAllergyDTO(Set<Allergy> allergies) {
        return allergies
                .stream()
                .map(allergy -> new AllergyDTO(allergy.getName(), allergy.getLevelString()))
                .collect(Collectors.toList());
    }

    public static List<DiagnosisDTO> mapToDiagnosisDTO(Set<Diagnosis> diagnoses) {
        return diagnoses
                .stream()
                .map(diagnosis -> new DiagnosisDTO(diagnosis.getName()))
                .collect(Collectors.toList());
    }

    static class AllergyDTO {
        String name;
        String level;

        private AllergyDTO(String name, String level) {
            this.name = name;
            this.level = level;
        }
    }

    static class DiagnosisDTO {
        String name;

        private DiagnosisDTO(String name) {
            this.name = name;
        }
    }
}
