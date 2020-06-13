package org.medihub.application.ports.incoming.medical_doctor.schedule;

public interface GetDoctorScheduleQuery {
    GetScheduleOutput getDoctorSchedule();

    GetScheduleOutput getDoctorSchedule(Long id);
}
