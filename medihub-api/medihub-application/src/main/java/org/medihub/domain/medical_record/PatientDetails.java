package org.medihub.domain.medical_record;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class PatientDetails {
    Integer height;
    Integer weight;
    BloodGroup bloodGroup;
    Dioptry dioptry;

    public PatientDetails(
            Integer height,
            Integer weight,
            String bloodType,
            Boolean rhPositive,
            Double leftDioptry,
            Double rightDioptry
    ) {
        this.height = height;
        this.weight = weight;
        this.bloodGroup = new BloodGroup(
                BloodGroup.BloodType.valueOf(bloodType),
                rhPositive);
        this.dioptry = new Dioptry(
                leftDioptry,
                rightDioptry);
    }


    @AllArgsConstructor
    static class BloodGroup {
        BloodType type;
        Boolean rhPositive;

        public String getBloodTypeString() {
            return type.toString();
        }

        private enum BloodType {
            A, B, AB, O;
        }
    }


    @AllArgsConstructor
    static class Dioptry {
        Double left;
        Double right;
    }
}
