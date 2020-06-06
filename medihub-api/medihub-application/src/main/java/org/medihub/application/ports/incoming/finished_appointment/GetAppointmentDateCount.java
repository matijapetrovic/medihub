package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;

@Value
public class GetAppointmentDateCount    {
    String date;
    Integer count;

    public static final Comparator<GetAppointmentDateCount> SortByMonth = new Comparator<GetAppointmentDateCount>() {
        @Override
        public int compare(GetAppointmentDateCount o1, GetAppointmentDateCount o2)
        {
            Month month1 = Month.valueOf(o1.getDate());
            Month month2 = Month.valueOf(o2.getDate());
            return month1.compareTo(month2);
        }
    };
}
