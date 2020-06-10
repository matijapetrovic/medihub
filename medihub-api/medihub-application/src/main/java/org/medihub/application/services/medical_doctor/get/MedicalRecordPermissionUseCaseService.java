package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.GetFinishedAppointmentsForDoctorAndPatient;
import org.medihub.application.ports.incoming.medical_doctor.CheckMedicalRecordPermissionUseCase;
import org.medihub.application.ports.outgoing.appointment.GetCurrentAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorByAccountIdPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctor;

@RequiredArgsConstructor
public class MedicalRecordPermissionUseCaseService implements CheckMedicalRecordPermissionUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetDoctorByAccountIdPort getDoctorByAccountIdPort;
    private final GetCurrentAppointmentPort getCurrentAppointmentPort;
    private final GetFinishedAppointmentsForDoctorAndPatient getFinishedAppointmentsForDoctorAndPatient;

    @Override
    public boolean doctorHasPermission(Long patientId) {
        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalDoctor doctor = getDoctorByAccountIdPort.getDoctor(account.getId());
        return ensureDoctorHasPermission(doctor.getId(), patientId);
    }

    private boolean doctorHasAppointment(Long doctorId, Long patientId) {
        if(getCurrentAppointmentPort.getCurrentAppointment(doctorId, patientId) == null)
            return false;
        return true;
    }

    private boolean ensureDoctorHasPermission(Long doctorId, Long patientId) {
        if(!doctorHasAppointment(doctorId, patientId))
            return false;

        if(getFinishedAppointmentsForDoctorAndPatient
                .getAppointmentsWhereDoctorExaminesPatient(doctorId, patientId).size() == 0)
            return false;
        return true;
    }
}
