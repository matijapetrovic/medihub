package org.medihub.domain.medical_nurse;

import org.medihub.domain.scheduling.DailyScheduleItem;

import java.time.LocalTime;

public class MedicalNurseScheduleItem extends DailyScheduleItem {
    public MedicalNurseScheduleItem(Long id, LocalTime time) {
        super(id, time);
    }
}
