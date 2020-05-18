package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.DailyScheduleItemOutput;
import org.medihub.application.ports.incoming.medical_doctor.DailyScheduleOutput;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorScheduleOutput;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorScheduleQuery;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorByAccountIdPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorSchedulePort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorSchedule;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GetDoctorScheduleService implements GetDoctorScheduleQuery {
    private final GetDoctorSchedulePort getDoctorSchedulePort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetDoctorByAccountIdPort getDoctorByAccountIdPort;

    @Override
    public GetDoctorScheduleOutput getDoctorSchedule() {

        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalDoctor medicalDoctor = getDoctorByAccountIdPort.getDoctor(account.getId());

        MedicalDoctorSchedule medicalDoctorSchedule = getDoctorSchedulePort.getDoctorSchedule(medicalDoctor.getId());
        GetDoctorScheduleOutput getDoctorScheduleOutput = createOutput(medicalDoctorSchedule);

        return getDoctorScheduleOutput;
    }

    public GetDoctorScheduleOutput createOutput(MedicalDoctorSchedule medicalDoctorSchedule) {

        Map<String, DailyScheduleOutput> dailySchedules = new HashMap<String, DailyScheduleOutput>();


        for(LocalDate date : medicalDoctorSchedule.getDailySchedules().keySet()) {
            DailyScheduleOutput dailyScheduleOutput = new DailyScheduleOutput();
            dailyScheduleOutput.id = medicalDoctorSchedule.getDailySchedules().get(date).getId();

            for(MedicalDoctorScheduleItem item : medicalDoctorSchedule.getDailySchedules().get(date).getScheduleItems()) {
                dailyScheduleOutput.scheduleItems.add(new DailyScheduleItemOutput(
                        item.getId(),
                        item.getTime().toString(),
                        item.getType().toString()
                ));
            }

            dailySchedules.put(date.toString(), dailyScheduleOutput);
        }

        return new GetDoctorScheduleOutput(dailySchedules);
    }

}
