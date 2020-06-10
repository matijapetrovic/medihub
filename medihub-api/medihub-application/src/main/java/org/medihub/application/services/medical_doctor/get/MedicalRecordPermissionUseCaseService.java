package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.GetFinishedAppointmentsForDoctorAndPatient;
import org.medihub.application.ports.incoming.medical_doctor.CheckMedicalRecordPermissionUseCase;
import org.medihub.application.ports.outgoing.appointment.GetCurrentAppointmentPort;

@RequiredArgsConstructor
public class MedicalRecordPermissionUseCaseService implements CheckMedicalRecordPermissionUseCase {
    private final GetCurrentAppointmentPort getCurrentAppointmentPort;
    private final GetFinishedAppointmentsForDoctorAndPatient getFinishedAppointmentsForDoctorAndPatient;

    @Override
    public boolean doctorHasPermission(Long doctorId, Long patientId) {
        return ensureDoctorHasPermission(doctorId, patientId);
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
