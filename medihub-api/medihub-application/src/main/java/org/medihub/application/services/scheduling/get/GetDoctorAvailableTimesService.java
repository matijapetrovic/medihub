package org.medihub.application.services.scheduling.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.scheduling.GetDoctorAvailableTimesQuery;
import org.medihub.application.ports.outgoing.doctor.GetDoctorWorkingTimePort;
import org.medihub.application.ports.outgoing.scheduling.LoadDoctorDailySchedulePort;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GetDoctorAvailableTimesService implements GetDoctorAvailableTimesQuery {
    private final LoadDoctorDailySchedulePort loadDoctorDailySchedulePort;
    private final GetDoctorWorkingTimePort getDoctorWorkingTimePort;

    @Override
    public List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        DailySchedule<MedicalDoctorScheduleItem> dailySchedule =
                loadDoctorDailySchedulePort.loadDailySchedule(doctorId, date);
        WorkingTime workingTime = getDoctorWorkingTimePort.getWorkingTime(doctorId);

        return buildAvailableTimesList(dailySchedule, workingTime);
    }

    private List<String> buildAvailableTimesList(DailySchedule<MedicalDoctorScheduleItem> dailySchedule,
                                                    WorkingTime workingTime) {
        List<String> availableTimes = new ArrayList<>();
        for (int i = 0; i < workingTime.getWorkingHours(); i++) {
            LocalTime currentTime = workingTime.getFrom().plusHours(i);
            if (dailySchedule.isAvailable(currentTime))
                availableTimes.add(currentTime.toString());
        }

        return availableTimes;
    }
}
