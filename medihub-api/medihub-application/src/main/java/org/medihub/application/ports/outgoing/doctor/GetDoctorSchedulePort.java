package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.medical_doctor.MedicalDoctorSchedule;

public interface GetDoctorSchedulePort {
    MedicalDoctorSchedule getDoctorSchedule(Long doctorId);
}
