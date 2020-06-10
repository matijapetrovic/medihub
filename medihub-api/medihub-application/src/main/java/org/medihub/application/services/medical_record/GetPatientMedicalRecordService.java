package org.medihub.application.services.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_record.AllergyDTO;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordOutput;
import org.medihub.application.ports.incoming.medical_record.GetPatientMedicalRecordQuery;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordPort;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.medical_record.Allergy;
import org.medihub.domain.medical_record.MedicalRecord;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPatientMedicalRecordService implements GetPatientMedicalRecordQuery {
    private final LoadMedicalRecordPort loadMedicalRecordPort;

    @Override
    public GetMedicalRecordOutput getPatientMedicalRecord(Long id) {
        MedicalRecord medicalRecord = loadMedicalRecordPort.loadMedicalRecord(id);

        return mapToOutput(medicalRecord);
    }

    private GetMedicalRecordOutput mapToOutput(MedicalRecord medicalRecord) {
        return new GetMedicalRecordOutput(
                medicalRecord.getId(),
                medicalRecord.getHeight(),
                medicalRecord.getWeight(),
                medicalRecord.getBloodType(),
                medicalRecord.getRhPositive(),
                medicalRecord.getLeftDioptry(),
                medicalRecord.getRightDioptry(),
                mapAllergies(medicalRecord.getAllergies()),
                null);
    }

    private List<AllergyDTO> mapAllergies(Set<Allergy> allergies) {
        return allergies
                .stream()
                .map(allergy -> new AllergyDTO(
                        allergy.getName(),
                        allergy.getLevelOrdinal() + 1,
                        allergy.getLevelLabel()))
                .collect(Collectors.toList());

    }
}
