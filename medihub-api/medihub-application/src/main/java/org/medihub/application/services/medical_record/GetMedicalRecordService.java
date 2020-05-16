package org.medihub.application.services.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordOutput;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordQuery;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_record.Allergy;
import org.medihub.domain.medical_record.MedicalRecord;
import org.medihub.domain.patient.Patient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetMedicalRecordService implements GetMedicalRecordQuery {
    private final LoadMedicalRecordPort loadMedicalRecordPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;

    @Override
    public GetMedicalRecordOutput getMedicalRecord() {
        Account account= getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());
        MedicalRecord medicalRecord = loadMedicalRecordPort.loadMedicalRecord(patient.getId());

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
                mapDiagnoses(medicalRecord.getDiagnosis()));
    }

    private Map<String, String> mapAllergies(Set<Allergy> allergies) {
        return allergies
                .stream()
                .collect(Collectors.toMap(
                        Allergy::getName,
                        Allergy::getLevelString));
    }

    private List<String> mapDiagnoses(Set<Diagnosis> diagnoses) {
        return diagnoses
                .stream()
                .map(Diagnosis::getName)
                .collect(Collectors.toList());
    }
}
