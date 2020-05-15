package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.WorkingTime;

public interface GetDoctorWorkingTimePort {
    WorkingTime getWorkingTime(Long doctorId);
}
