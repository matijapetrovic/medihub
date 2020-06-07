package org.medihub.domain.medical_record;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.medihub.application.ports.incoming.medical_record.BloodTypeOutput;

import java.util.ArrayList;
import java.util.List;

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
    public static class BloodGroup {
        BloodType type;
        Boolean rhPositive;

        public String getBloodTypeString() {
            return type != null ? type.toString() : null;
        }

        public static List<BloodTypeOutput> getBloodTypes() {
            List<BloodTypeOutput> output = new ArrayList<BloodTypeOutput>();

            for(BloodType type : BloodType.values()){
                output.add(new BloodTypeOutput(type.toString(), type.ordinal()));
            }

            return output;
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
