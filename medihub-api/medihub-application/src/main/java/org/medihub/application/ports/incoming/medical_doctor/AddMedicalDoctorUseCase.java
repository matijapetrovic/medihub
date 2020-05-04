package org.medihub.application.ports.incoming.medical_doctor;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.ports.incoming.patient.RegisterPatientUseCase;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.InsuranceNumber;
import org.medihub.common.validation.annotations.Password;
import org.medihub.common.validation.annotations.TelephoneNumber;
import org.medihub.domain.Appointment;
import org.medihub.domain.Clinic;
import org.medihub.domain.MedicalDoctor;
import org.medihub.domain.WorkingCalendar;
import org.medihub.domain.identity.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public interface AddMedicalDoctorUseCase {
    void addDoctor(AddMedicalDoctorCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddMedicalDoctorCommand extends SelfValidating<AddMedicalDoctorUseCase.AddMedicalDoctorCommand> {

        Long id;
        Account account;
        WorkingCalendar workingCalendar;
        Clinic clinic;
        Set<Appointment> appointments;

        public AddMedicalDoctorCommand(
                Long id,
                Account account,
                WorkingCalendar workingCalendar,
                Clinic clinic,
                Set<Appointment> appointments) {
            this.id = id;
            this.account = new Account(
                    account.getId(),
                    account.getEmail(),
                    account.getPassword(),
                    account.getPersonalInfo(),
                    account.isPasswordChanged(),
                    account.getAuthorities()
            );
            this.workingCalendar = workingCalendar;
            this.clinic = clinic;
            this.appointments = appointments;
        }
    }
}
