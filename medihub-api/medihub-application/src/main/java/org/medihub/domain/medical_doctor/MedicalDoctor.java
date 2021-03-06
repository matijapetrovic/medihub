package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.MedicalStaff;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.reviewing.Rating;


@Getter
public class MedicalDoctor extends MedicalStaff {
    private AppointmentType specialization;
    private Rating rating;
    private Boolean archived;

    public MedicalDoctor(
            Long id,
            PersonalInfo personalInfo,
            Clinic clinic,
            WorkingTime workingTime,
            AppointmentType appointmentType,
            Rating rating,
            Boolean archived) {
        super(id, personalInfo, clinic, workingTime);
        this.specialization = appointmentType;
        this.rating = rating;
        this.archived = archived;
    }

    public String getFirstName() {
        return getPersonalInfo().getFirstName();
    }

    public String getLastName() {
        return getPersonalInfo().getLastName();
    }

    public String getFullName() { return String.format("%s %s", getFirstName(), getLastName()); }

    public void archive() { this.archived = true; this.getPersonalInfo().removeAccount(); }
}
