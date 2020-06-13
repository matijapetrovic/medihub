package org.medihub.application.services.medical_nurse;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.schedule.*;
import org.medihub.application.ports.incoming.medical_nurse.GetNurseScheduleQuery;
import org.medihub.application.ports.outgoing.authentication.AuthenticationPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.medical_nurse.GetMedicalNurseByAccountIdPort;
import org.medihub.application.ports.outgoing.medical_nurse.GetNurseSchedulePort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.*;
import org.medihub.domain.medical_nurse.MedicalNurse;
import org.medihub.domain.medical_nurse.MedicalNurseSchedule;
import org.medihub.domain.medical_nurse.MedicalNurseScheduleItem;
import org.medihub.domain.medical_nurse.MedicalNurseVacationScheduleItem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GetNurseScheduleService implements GetNurseScheduleQuery {

    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetMedicalNurseByAccountIdPort getMedicalNurseByAccountIdPort;
    private final GetNurseSchedulePort getNurseSchedulePort;

    @Override
    public GetScheduleOutput getSchedule(Long id) {
        Account account;
        MedicalNurse medicalNurse;

        if(id == null) {
            account = getAuthenticatedPort.getAuthenticated();
            medicalNurse = getMedicalNurseByAccountIdPort.getMedicalNurseByAccountId(account.getId());
            id = medicalNurse.getId();
        }

        return createOutput(getNurseSchedulePort.getSchedule(id));
    }


    public GetScheduleOutput createOutput(MedicalNurseSchedule medicalDoctorSchedule) {

        Map<String, DailyScheduleOutput> dailySchedules = new HashMap<String, DailyScheduleOutput>();


        for(LocalDate date : medicalDoctorSchedule.getDailySchedules().keySet()) {
            DailyScheduleOutput dailyScheduleOutput = new DailyScheduleOutput();
            dailyScheduleOutput.id = medicalDoctorSchedule.getDailySchedules().get(date).getId();

            for(MedicalNurseScheduleItem item : medicalDoctorSchedule.getDailySchedules().get(date).getScheduleItems()) {
                dailyScheduleOutput.scheduleItems.add(getItem(item));
            }

            dailySchedules.put(date.toString(), dailyScheduleOutput);
        }

        return new GetScheduleOutput(dailySchedules);
    }

    public DailyScheduleItemOutput getItem(MedicalNurseScheduleItem item) {
        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType type = item.getType();

        MedicalNurseVacationScheduleItem vacationItem = (MedicalNurseVacationScheduleItem) item;
        return new VacationScheduleItemOutput(
                vacationItem.getId(),
                vacationItem.getTime().toString(),
                vacationItem.getType().toString(),
                vacationItem.getEndDate().toString()
        );
    }
}
