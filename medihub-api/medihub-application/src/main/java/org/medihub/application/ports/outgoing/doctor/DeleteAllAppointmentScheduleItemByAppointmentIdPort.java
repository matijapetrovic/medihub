package org.medihub.application.ports.outgoing.doctor;

public interface DeleteAllAppointmentScheduleItemByAppointmentIdPort {
    void deleteAll(Long operationId);
}
