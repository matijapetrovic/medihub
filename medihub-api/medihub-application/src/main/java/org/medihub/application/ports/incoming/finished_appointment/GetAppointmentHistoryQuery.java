package org.medihub.application.ports.incoming.finished_appointment;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public interface GetAppointmentHistoryQuery {
    List<GetAppointmentHistoryOutput> getAppointmentHistory();
    List<GetAppointmentDateCount> getClinicAppointmentHistory(FinishedAppointmentQuery finishedAppointmentQuery);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class FinishedAppointmentQuery extends SelfValidating<GetAppointmentHistoryQuery.FinishedAppointmentQuery> {
        @NotNull
        String type;
        @NotNull
        LocalDate date;

        public FinishedAppointmentQuery(
                String type,
                String date) {
            this.type = type;
            this.date = LocalDate.parse(date);
            this.validateSelf();
        }
    }
}
