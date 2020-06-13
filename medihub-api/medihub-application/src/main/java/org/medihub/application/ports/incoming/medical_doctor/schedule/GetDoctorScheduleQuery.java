package org.medihub.application.ports.incoming.medical_doctor.schedule;

import org.medihub.application.exceptions.NotFoundException;

public interface GetDoctorScheduleQuery {
    GetScheduleOutput getDoctorSchedule() throws NotFoundException;

    GetScheduleOutput getDoctorSchedule(Long id) throws NotFoundException;
}
