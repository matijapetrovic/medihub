package org.medihub.application.ports.outgoing.doctor;

public interface DeleteAppointmentScheduleItemByDoctorIdPort {
    void delete(Long doctorId);
}
