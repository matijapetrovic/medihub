package org.medihub.application.ports.outgoing.doctor;

public interface CountNumberOfDoctorsWithApppointmentTypePort {
    Long countNumber(Long appointmentTypeId);
}
