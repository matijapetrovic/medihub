package org.medihub.application.services.clinic.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic.GetAppointmentPriceUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic.GetAppointmentPricePort;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.domain.Money;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.ClinicAdmin;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class GetAppointmentPriceService implements GetAppointmentPriceUseCase {
    private final GetAppointmentPricePort getAppointmentPricePort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public Map<AppointmentType, Money> getPrices() {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinic = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return getAppointmentPricePort.getPrices(clinic.getId());
    }
}
