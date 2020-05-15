package org.medihub.domain.medical_record;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class PatientDetails {
    Integer height;
    Integer weight;
    BloodGroup bloodGroup;
    Dioptry dioptry;

    private PatientDetails() {
        this.height = null;
        this.weight = null;
        this.bloodGroup = new BloodGroup();
        this.dioptry = new Dioptry();
    }

    public static PatientDetails create() {
        return new PatientDetails();
    }

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
                bloodType != null ? BloodGroup.BloodType.valueOf(bloodType) : null,
                rhPositive);
        this.dioptry = new Dioptry(
                leftDioptry,
                rightDioptry);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    static class BloodGroup {
        BloodType type;
        Boolean rhPositive;

        public String getBloodTypeString() {
            return type != null ? type.toString() : null;
        }

        private enum BloodType {
            A, B, AB, O;
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    static class Dioptry {
        Double left;
        Double right;
    }
}
