package org.medihub.application.ports.incoming.finished_appointment;

import org.medihub.domain.appointment.FinishedAppointment;

import java.util.List;

public interface GetFinishedAppointmentsForDoctorAndPatient {
    List<FinishedAppointment> getAppointmentsWhereDoctorExaminesPatient(Long doctorId, Long patientId);
}
