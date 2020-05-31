package org.medihub.application.services.finished_appointment;

import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentDateCount;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery.FinishedAppointmentQuery;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class FinishedAppointmentCounter<T> {
    private String type;

    public FinishedAppointmentCounter(String type) {
        this.type = type;
    }

    public List<GetAppointmentDateCount> countAppearance(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery) {
        //finishedAppointments = filterByType(finishedAppointments, finishedAppointmentQuery);
        if (finishedAppointmentQuery.getType().equals("day")) {
            return countDailyAppearance(finishedAppointments);
        } else if (finishedAppointmentQuery.getType().equals("month")) {
            return countMonthlyAppearance(finishedAppointments);
        } else {
            return countAnnualAppearance(finishedAppointments);
        }
    }

    private List<GetAppointmentDateCount> countDailyAppearance(List<FinishedAppointment> finishedAppointments) {
        HashMap<LocalTime, GetAppointmentDateCount> countDict = new HashMap<>();
        for(FinishedAppointment item: finishedAppointments) {
            LocalTime time = item.getAppointment().getTime();
            if (!countDict.containsKey(time)){
                countDict.put(time, new GetAppointmentDateCount(time.toString() , 1));
            } else {
                countDict.put(time, new GetAppointmentDateCount(time.toString(), countDict.get(time).getCount() + 1));
            }
        }
        return sortByType(new ArrayList<GetAppointmentDateCount>(countDict.values()));
    }

    private List<GetAppointmentDateCount> countMonthlyAppearance(List<FinishedAppointment> finishedAppointments) {
        HashMap<LocalDate, GetAppointmentDateCount> countDict = new HashMap<>();
        for(FinishedAppointment item: finishedAppointments) {
            LocalDate date = item.getAppointment().getDate();
            if (!countDict.containsKey(date)){
                countDict.put(date, new GetAppointmentDateCount(date.toString() , 1));
            } else {
                countDict.put(date, new GetAppointmentDateCount(date.toString(),countDict.get(date).getCount() + 1));
            }
        }
       return sortByType(new ArrayList<GetAppointmentDateCount>(countDict.values()));
    }

    private List<GetAppointmentDateCount> countAnnualAppearance(List<FinishedAppointment> finishedAppointments) {
        HashMap<Month, GetAppointmentDateCount> countDict = new HashMap<>();
        for(FinishedAppointment item: finishedAppointments) {
            Month month = item.getAppointment().getDate().getMonth();
            if (!countDict.containsKey(month)){
                countDict.put(month, new GetAppointmentDateCount(month.toString() , 1));
            } else {
                countDict.put(month, new GetAppointmentDateCount(month.toString(), countDict.get(month).getCount() + 1));
            }
        }
        return sortByType(new ArrayList<GetAppointmentDateCount>(countDict.values()));
    }

    private List<GetAppointmentDateCount> sortByType(List<GetAppointmentDateCount> finishedAppointments) {
        if(this.type.equals("year")) {
            Collections.sort(finishedAppointments, Comparator.comparing(GetAppointmentDateCount::getAnnualValue));
        } else if(this.type.equals("month")) {
            Collections.sort(finishedAppointments, Comparator.comparing(GetAppointmentDateCount::getMonthlyValue));
        }else {
            Collections.sort(finishedAppointments, Comparator.comparing(GetAppointmentDateCount::getDailyValue));
        }
        return finishedAppointments;
    }
    private List<FinishedAppointment> filterByType(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery)
    {
        if(finishedAppointmentQuery.getType().equals("year")) {
            return filterByYear(finishedAppointments, finishedAppointmentQuery);
        } else if(finishedAppointmentQuery.getType().equals("month")) {
            return filterByMonth(finishedAppointments, finishedAppointmentQuery);
        }else {
            return filterByDay(finishedAppointments, finishedAppointmentQuery);
        }
    }

    private List<FinishedAppointment> filterByYear(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery
    ) {
        return finishedAppointments
                .stream()
                .filter(item -> item.getAppointment().getDate().getYear() == finishedAppointmentQuery.getDate().getYear())
                .collect(Collectors.toList());
    }

    private List<FinishedAppointment> filterByMonth(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery
    ) {
        return finishedAppointments
                .stream()
                .filter(item ->
                        item.getAppointment().getDate().getYear() == finishedAppointmentQuery.getDate().getYear() &&
                                item.getAppointment().getDate().getMonth() == finishedAppointmentQuery.getDate().getMonth())
                .collect(Collectors.toList());
    }

    private List<FinishedAppointment> filterByDay(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery
    ) {
        return finishedAppointments
                .stream()
                .filter(item ->
                        item.getAppointment().getDate().equals(finishedAppointmentQuery.getDate())).collect(Collectors.toList());
    }

}
