package org.medihub.application.ports.incoming.medical_nurse;

import org.medihub.application.ports.incoming.medical_doctor.schedule.GetScheduleOutput;

public interface GetNurseScheduleQuery {
    GetScheduleOutput getSchedule(Long id);
}
