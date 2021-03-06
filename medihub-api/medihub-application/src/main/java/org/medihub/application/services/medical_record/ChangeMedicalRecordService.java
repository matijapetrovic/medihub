package org.medihub.application.services.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_record.dto.AllergyDTO;
import org.medihub.application.ports.incoming.medical_record.ChangeMedicalRecordUseCase;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordOutput;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordByIdPort;
import org.medihub.application.ports.outgoing.medical_record.SaveMedicalRecordPort;
import org.medihub.domain.medical_record.Allergy;
import org.medihub.domain.medical_record.MedicalRecord;
import org.medihub.domain.medical_record.PatientDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ChangeMedicalRecordService implements ChangeMedicalRecordUseCase {
    private final LoadMedicalRecordByIdPort loadMedicalRecordByIdPort;
    private final SaveMedicalRecordPort saveMedicalRecordPort;

    @Override
    public void changeMedicalRecord(ChangeMedicalRecordCommand command) {
        MedicalRecord medicalRecord = loadMedicalRecordByIdPort.loadMedicalRecordById(command.getId());
        change(medicalRecord, command);
        saveMedicalRecordPort.saveMedicalRecordPort(medicalRecord);
    }

    private void change(MedicalRecord medicalRecord, ChangeMedicalRecordCommand command) {
        medicalRecord.getPatientDetails().setHeight(command.getHeight());
        medicalRecord.getPatientDetails().setWeight(command.getWeight());
        medicalRecord.getPatientDetails().getBloodGroup().setRhPositive(command.getRhPositive());
        medicalRecord.getPatientDetails().getBloodGroup().setType(
                PatientDetails.BloodGroup.BloodType.valueOf(command.getBloodType()));
        medicalRecord.getPatientDetails().getDioptry().setLeft(command.getLeftDioptry());
        medicalRecord.getPatientDetails().getDioptry().setRight(command.getRightDioptry());

        Set<Allergy> allergies = new HashSet<>();

        for(AllergyDTO aDto : command.getAllergies()) {
            allergies.add(new Allergy(aDto.getName(),
                                      Allergy.Level.values()[aDto.getLevel() - 1]));
        }

        medicalRecord.setAllergies(allergies);
    }
}
