package org.medihub.application.services.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.registration.AcceptRegistrationUseCase;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.medical_record.SaveMedicalRecordPort;
import org.medihub.application.ports.outgoing.patient.SavePatientPort;
import org.medihub.application.ports.outgoing.registration_request.DeleteRegistrationRequestPort;
import org.medihub.application.ports.outgoing.registration_request.LoadRegistrationRequestPort;
import org.medihub.domain.medical_record.MedicalRecord;
import org.medihub.domain.patient.Patient;
import org.medihub.domain.patient.RegistrationRequest;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class AcceptRegistrationService implements AcceptRegistrationUseCase {
    private final LoadRegistrationRequestPort loadRegistrationRequestPort;
    private final SavePatientPort savePatientPort;
    private final SaveMedicalRecordPort saveMedicalRecordPort;
    private final DeleteRegistrationRequestPort deleteRegistrationRequestPort;
    private final SendEmailPort sendEmailPort;

    @Override
    @Transactional
    public void acceptRegistration(@NotNull Long requestId) throws NotFoundException {
        RegistrationRequest request = loadRegistrationRequestPort.loadById(requestId);
        Patient patient = request.accept();
        patient = savePatientPort.savePatient(patient);
        MedicalRecord record = MedicalRecord.create(patient);

        saveMedicalRecordPort.saveMedicalRecordPort(record);
        deleteRegistrationRequestPort.deleteById(request.getId());
        notifyPatient(patient);
    }

    private void notifyPatient(Patient patient) {
        String to = "medihub.mail@gmail.com";
        String subject = "Registration request accepted";
        String text = String.format("Your registration request has been accepted. Please visit this link to activate" +
                "your account: http://localhost:8080/activate/%d", patient.getPersonalInfo().getAccount().getId());
        sendEmailPort.sendEmail(to, subject, text);
    }
}
