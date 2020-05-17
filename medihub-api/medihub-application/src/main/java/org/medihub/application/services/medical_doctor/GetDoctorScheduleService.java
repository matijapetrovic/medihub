package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorScheduleOutput;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorScheduleQuery;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorByAccountIdPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorSchedule;

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

        return new GetDoctorScheduleOutput(medicalDoctorSchedule.getDailySchedules());
    }

}
