package org.medihub.application.services.clinic.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.AddPriceToAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic.SaveClinicPort;
import org.medihub.domain.Money;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;

@RequiredArgsConstructor
public class AddPriceService implements AddPriceToAppointmentTypeUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final SaveClinicPort saveClinicPort;
    private final LoadAppointmentTypePort loadAppointmentTypePort;

    @Override
    public void addPrice(AddPriceCommand addPriceCommand) {
        Account account = getAuthenticatedPort.getAuthenticated();
        Clinic clinic = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId()).getClinic();
        AppointmentType appointmentType = loadAppointmentTypePort.loadAppointmentType(addPriceCommand.getAppointmentTypeId());

        clinic.addPrice(appointmentType, Money.of(addPriceCommand.getPrice()));
        saveClinicPort.saveClinic(clinic);
    }
}
