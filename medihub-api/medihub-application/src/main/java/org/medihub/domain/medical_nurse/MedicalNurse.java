package org.medihub.domain.medical_nurse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.MedicalStaff;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.Schedule;

@Getter
public class MedicalNurse extends MedicalStaff {
    public MedicalNurse(
            Long id,
            Account account,
            Clinic clinic,
            WorkingTime workingTime) {
        super(id, account, clinic, workingTime);
    }
}
