package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.schedule.*;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorByAccountIdPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorSchedulePort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GetDoctorScheduleService implements GetDoctorScheduleQuery {
    private final GetDoctorSchedulePort getDoctorSchedulePort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetDoctorByAccountIdPort getDoctorByAccountIdPort;

    @Override
    public GetScheduleOutput getDoctorSchedule() {
        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalDoctor medicalDoctor = getDoctorByAccountIdPort.getDoctor(account.getId());

        MedicalDoctorSchedule medicalDoctorSchedule = getDoctorSchedulePort.getDoctorSchedule(medicalDoctor.getId());
        GetScheduleOutput getScheduleOutput = createOutput(medicalDoctorSchedule);

        return getScheduleOutput;
    }

    @Override
    public GetScheduleOutput getDoctorSchedule(Long id) {
        MedicalDoctorSchedule medicalDoctorSchedule = getDoctorSchedulePort.getDoctorSchedule(id);
        GetScheduleOutput getScheduleOutput = createOutput(medicalDoctorSchedule);

        return getScheduleOutput;
    }

    public GetScheduleOutput createOutput(MedicalDoctorSchedule medicalDoctorSchedule) {

        Map<String, DailyScheduleOutput> dailySchedules = new HashMap<String, DailyScheduleOutput>();


        for(LocalDate date : medicalDoctorSchedule.getDailySchedules().keySet()) {
            DailyScheduleOutput dailyScheduleOutput = new DailyScheduleOutput();
            dailyScheduleOutput.id = medicalDoctorSchedule.getDailySchedules().get(date).getId();

            for(MedicalDoctorScheduleItem item : medicalDoctorSchedule.getDailySchedules().get(date).getScheduleItems()) {
                dailyScheduleOutput.scheduleItems.add(getItem(item));
            }

            dailySchedules.put(date.toString(), dailyScheduleOutput);
        }

        return new GetScheduleOutput(dailySchedules);
    }

    public DailyScheduleItemOutput getItem(MedicalDoctorScheduleItem item) {
        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType type = item.getType();

        switch(type) {
            case APPOINTMENT:
                MedicalDoctorAppointmentScheduleItem castItem = (MedicalDoctorAppointmentScheduleItem) item;
                return new AppointmentScheduleItemOutput(
                    castItem.getId(),
                    castItem.getTime().toString(),
                    castItem.getType().toString(),
                    castItem.getAppointment());
            case LEAVE:
            case VACATION:
                MedicalDoctorVacationScheduleItem vacationItem = (MedicalDoctorVacationScheduleItem) item;
                return new VacationScheduleItemOutput(
                        vacationItem.getId(),
                        vacationItem.getTime().toString(),
                        vacationItem.getType().toString(),
                        vacationItem.getEndDate().toString()
                );
        }

        return null;

    }

}
