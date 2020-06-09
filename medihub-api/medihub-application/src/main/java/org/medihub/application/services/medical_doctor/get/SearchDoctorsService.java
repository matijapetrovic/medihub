package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.SearchDoctorsOutput;
import org.medihub.application.ports.incoming.medical_doctor.SearchDoctorsQuery;
import org.medihub.application.ports.outgoing.doctor.SearchDoctorsPort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadDoctorDailySchedulePort;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchDoctorsService implements SearchDoctorsQuery {
    private final SearchDoctorsPort searchDoctorsPort;
    private final LoadDoctorDailySchedulePort loadDoctorDailySchedulePort;

    @Override
    public List<SearchDoctorsOutput> searchDoctors(Long clinicId, LocalDate date, Long appointmentTypeId) {
        List<MedicalDoctor> doctors = searchDoctorsPort.searchDoctors(clinicId, date, appointmentTypeId);
        return mapToOutput(doctors, date);
    }

    private List<SearchDoctorsOutput> mapToOutput(List<MedicalDoctor> doctors, LocalDate date) {
        return doctors
                .stream()
                .map(medicalDoctor -> new SearchDoctorsOutput(
                        medicalDoctor.getId(),
                        medicalDoctor.getFirstName(),
                        medicalDoctor.getLastName(),
                        medicalDoctor.getRating().getRating(),
                        medicalDoctor.getRating().getCount(),
                        medicalDoctor.getWorkingTime().getFrom().toString(),
                        medicalDoctor.getWorkingTime().getTo().toString(),
                        medicalDoctor.getClinic().getPrice(medicalDoctor.getSpecialization()).getAmount(),
                        date == null ? null : getAvailableTimes(medicalDoctor, date)))
                .collect(Collectors.toList());
    }

    private List<String> getAvailableTimes(MedicalDoctor doctor, LocalDate date) {
        DailySchedule<MedicalDoctorScheduleItem> dailySchedule =
                loadDoctorDailySchedulePort.loadDailySchedule(doctor.getId(), date);

        return buildAvailableTimesList(dailySchedule, doctor.getWorkingTime());
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
