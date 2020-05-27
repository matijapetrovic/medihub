package org.medihub.application.ports.incoming.clinic;

import org.medihub.domain.Money;
import org.medihub.domain.appointment.AppointmentType;

import java.util.Map;

public interface GetAppointmentPriceUseCase {
    Map<AppointmentType, Money> getPrices();
}
