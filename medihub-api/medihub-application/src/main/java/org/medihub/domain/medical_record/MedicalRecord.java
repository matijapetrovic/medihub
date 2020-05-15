package org.medihub.domain.medical_record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.Diagnosis;

import java.util.Set;

@AllArgsConstructor
@Getter
public class MedicalRecord {
    private Long id;
    private PatientDetails patientDetails;
    private Set<Diagnosis> diagnosis;
    private Set<Allergy> allergies;

    public Integer getHeight() {
        return patientDetails.getHeight();
    }

    public Integer getWeight() {
        return patientDetails.getWeight();
    }

    public String getBloodType() {
        return patientDetails.getBloodGroup().getBloodTypeString();
    }

    public Boolean getRhPositive() {
        return patientDetails.getBloodGroup().rhPositive;
    }

    public Double getLeftDioptry() {
        return patientDetails.getDioptry().left;
    }

    public Double getRightDioptry() {
        return patientDetails.getDioptry().right;
    }
}
