package org.medihub.application.ports.incoming.medical_doctor.schedule;

public interface GetDoctorScheduleQuery {
    GetDoctorScheduleOutput getDoctorSchedule();

    GetDoctorScheduleOutput getDoctorSchedule(Long id);
}
