package org.medihub.application.services.medical_record;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_record.dto.AllergyDTO;
import org.medihub.application.ports.incoming.medical_record.dto.FinishedAppointmentDTO;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordOutput;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordQuery;
import org.medihub.application.ports.incoming.medical_record.dto.PrescriptionDTO;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.finished_appointment.GetFinishedAppointmentsPort;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionsPort;
import org.medihub.domain.Prescription;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.medical_record.Allergy;
import org.medihub.domain.medical_record.MedicalRecord;
import org.medihub.domain.patient.Patient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetMedicalRecordService implements GetMedicalRecordQuery {
    private final LoadMedicalRecordPort loadMedicalRecordPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final GetFinishedAppointmentsPort getFinishedAppointmentsPort;
    private final GetPrescriptionsPort getPrescriptionsPort;

    @Override
    public GetMedicalRecordOutput getMedicalRecord() {
        Account account= getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());
        MedicalRecord medicalRecord = loadMedicalRecordPort.loadMedicalRecord(patient.getId());
        List<FinishedAppointment> finishedAppointments =
                getFinishedAppointmentsPort.getFinishedAppointments(patient.getId());

        return mapToOutput(patient, medicalRecord, finishedAppointments);
    }

    private GetMedicalRecordOutput mapToOutput(Patient patient, MedicalRecord medicalRecord, List<FinishedAppointment> finishedAppointments) {
        return new GetMedicalRecordOutput(
                medicalRecord.getId(),
                patient.getInsuranceNumber(),
                medicalRecord.getHeight(),
                medicalRecord.getWeight(),
                medicalRecord.getBloodType(),
                medicalRecord.getRhPositive(),
                medicalRecord.getLeftDioptry(),
                medicalRecord.getRightDioptry(),
                mapAllergies(medicalRecord.getAllergies()),
                mapFinishedAppointments(finishedAppointments));
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

    private List<FinishedAppointmentDTO> mapFinishedAppointments(List<FinishedAppointment> finishedAppointments) {
        return finishedAppointments
                .stream()
                .map(fa -> new FinishedAppointmentDTO(
                        fa.getAppointment().getDate().toString(),
                        fa.getAppointment().getDoctor().getFullName(),
                        fa.getDiagnosis().getName(),
                        fa.getDescription(),
                        mapPrescriptions(getPrescriptionsPort.getPrescriptionsForAppointment(fa.getId()))))
                .collect(Collectors.toList());
    }

    private List<PrescriptionDTO> mapPrescriptions(List<Prescription> prescriptions) {
        return prescriptions
                .stream()
                .map(prescription -> new PrescriptionDTO(
                        prescription.getDrug().getName(),
                        prescription.getMedicalNurse() != null))
                .collect(Collectors.toList());
    }
}
