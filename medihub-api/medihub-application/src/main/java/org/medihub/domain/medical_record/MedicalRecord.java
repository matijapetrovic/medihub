package org.medihub.domain.medical_record;

import lombok.*;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.patient.Patient;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MedicalRecord {
    private Long id;
    private Patient patient;
    private PatientDetails patientDetails;
    private Set<Allergy> allergies;

    public static MedicalRecord create(Patient patient) {
        MedicalRecord record = new MedicalRecord();
        record.id = null;
        record.patient = patient;
        record.patientDetails = PatientDetails.create();
        record.allergies = new HashSet<>();

        return record;
    }

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
