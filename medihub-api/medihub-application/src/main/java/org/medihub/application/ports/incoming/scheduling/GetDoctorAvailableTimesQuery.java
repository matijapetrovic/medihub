package org.medihub.application.ports.incoming.scheduling;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface GetDoctorAvailableTimesQuery {
    List<String> getAvailableTimes(Long doctorId, LocalDate date);
}
