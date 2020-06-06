package org.medihub.application.services.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.FinishedAppointmentProfitResponse;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentDateCount;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery;
import org.medihub.application.ports.incoming.finished_appointment.GetFinishedAppointmentProfitUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.finished_appointment.GetFinishedAppointmentsPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.reviewing.LoadClinicReviewPort;
import org.medihub.application.ports.outgoing.reviewing.LoadDoctorReviewPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic.ClinicAdmin;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
public class GetFinishedAppointmentProfitService implements GetFinishedAppointmentProfitUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetFinishedAppointmentsPort getFinishedAppointmentsPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadClinicPort loadClinicPort;

    @Override
    public FinishedAppointmentProfitResponse getProfit(GetFinishedAppointmentProfitCommand getFinishedAppointmentProfitCommand) {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        Clinic clinic = loadClinicPort.loadClinic(clinicAdmin.getClinic().getId());
        List<FinishedAppointment> finishedAppointments =
                getFinishedAppointmentsPort
                        .getAllFinishedAppointmentsForCurrentClinic(clinicAdmin.getClinic().getId());

        return makeResponse(getFinishedAppointmentProfitCommand, finishedAppointments, clinic);
    }
    private FinishedAppointmentProfitResponse makeResponse(
            GetFinishedAppointmentProfitCommand command,
            List<FinishedAppointment> finishedAppointments,
            Clinic clinic)
    {
        Long monthsBetween = getMonthsBetween(command.getFrom(), command.getTo());
        LocalDate longTermStartDate = getLargeTermStartDate(monthsBetween, command.getTo());
        String description = evaluateLongTermDescription(monthsBetween);
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal longTermPrice = BigDecimal.ZERO;

        for (FinishedAppointment fa : finishedAppointments) {
            LocalDate appointmentDate = fa.getAppointment().getDate();
            AppointmentType type = fa.getAppointment().getDoctor().getSpecialization();
            if(!appointmentDate.isBefore(command.getFrom()) &&
                !appointmentDate.isAfter(command.getTo()))
            {
                price = price.add(clinic.getAppointmentPrices().get(type).getAmount());
            }
            if(!appointmentDate.isBefore(longTermStartDate) &&
                    !appointmentDate.isAfter(command.getTo())) {
                longTermPrice = longTermPrice.add(clinic.getAppointmentPrices().get(type).getAmount());
            }
        }
        return new FinishedAppointmentProfitResponse(price, longTermPrice, description);
    }

    private LocalDate getLargeTermStartDate(Long monthsBetween, LocalDate maxDate) {
        Long longTerm = evaluateLongTerm(monthsBetween);
        return maxDate.minusMonths(longTerm);
    }

    private Long getMonthsBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.MONTHS.between(YearMonth.from(from), YearMonth.from(to));
    }

    private Long evaluateLongTerm(Long monthsBetween) {
        if(monthsBetween < 1) {
            return 3L;
        } else if(monthsBetween > 1 && monthsBetween < 3) {
            return 6L;
        } else {
            return 12L;
        }
    }

    private String evaluateLongTermDescription(Long monthsBetween) {
        if(monthsBetween < 1) {
            return "Last three months";
        } else if(monthsBetween > 1 && monthsBetween < 3) {
            return "Las six months";
        } else {
            return "Last year";
        }
    }
}
