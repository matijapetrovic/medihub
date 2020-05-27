package org.medihub.application.ports.outgoing.clinic;

import org.medihub.domain.Money;
import org.medihub.domain.appointment.AppointmentType;

import java.util.Map;

public interface GetAppointmentPricePort {
    Map<AppointmentType, Money> getPrices(Long clinicId);
}
