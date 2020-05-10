package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.identity.Account;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalDoctor extends  MedicalStaff {
    private AppointmentType specialization;

    public MedicalDoctor(
            Long id,
            Account account,
            WorkingCalendar workingCalendar,
            Clinic clinic,
            WorkingTime workingTime,
            AppointmentType appointmentType
    ) {
        super(id, account, workingCalendar, clinic, workingTime);
        this.specialization = appointmentType;
    }

    public String getFirstName() {
        return getAccount().getFirstName();
    }

    public String getLastName() {
        return getAccount().getLastName();
    }
}
