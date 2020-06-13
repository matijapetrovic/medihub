package org.medihub.application.services.scheduling.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.scheduling.GetDoctorAvailableTimesQuery;
import org.medihub.application.ports.outgoing.doctor.GetDoctorWorkingTimePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadDoctorDailySchedulePort;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetDoctorAvailableTimesService implements GetDoctorAvailableTimesQuery {
    private final LoadDoctorDailySchedulePort loadDoctorDailySchedulePort;
    private final GetDoctorWorkingTimePort getDoctorWorkingTimePort;

    @Override
    public List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        DailySchedule<MedicalDoctorScheduleItem> dailySchedule =
                loadDoctorDailySchedulePort.loadDailySchedule(doctorId, date);
        WorkingTime workingTime = getDoctorWorkingTimePort.getWorkingTime(doctorId);

        List<LocalTime> availableTimes = dailySchedule.getAvailableTimes(workingTime);
        if (date.equals(LocalDate.now().plusDays(1)))
            filterAvailableTimesForToday(availableTimes);

        return mapToOutput(availableTimes);
    }

    private void filterAvailableTimesForToday(List<LocalTime> availableTimes) {
        availableTimes.removeIf(time -> time.isBefore(LocalTime.now()));
    }

    private List<String> mapToOutput(List<LocalTime> availableTimes) {
        return availableTimes
                .stream()
                .map(LocalTime::toString)
                .collect(Collectors.toList());
    }
}
