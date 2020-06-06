package org.medihub.application.services.finished_appointment;

import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentDateCount;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery.FinishedAppointmentQuery;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class FinishedAppointmentCounter {
    private String type;

    public FinishedAppointmentCounter(String type) {
        this.type = type;
    }

    public List<GetAppointmentDateCount> countAppearance(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery) {
        finishedAppointments = filterByType(finishedAppointments, finishedAppointmentQuery);
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
        return sortByType(new ArrayList<GetAppointmentDateCount>(fillMapWithAllTimes(countDict).values()));
    }

    HashMap<LocalTime, GetAppointmentDateCount>  fillMapWithAllTimes(HashMap<LocalTime, GetAppointmentDateCount> map) {
        for(int i=0; i < 24;i++) {
            LocalTime time = LocalTime.of(i, 0);
            if(!map.containsKey(time)) {
                map.put(time, new GetAppointmentDateCount(time.toString(), 0));
            }
        }
        return map;
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
       return sortByType(new ArrayList<GetAppointmentDateCount>(fillMapWithAllDays(countDict).values()));
    }

    HashMap<LocalDate, GetAppointmentDateCount> fillMapWithAllDays(HashMap<LocalDate, GetAppointmentDateCount> map) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();

        for (int i = 1; i < daysInMonth;i++) {
            LocalDate date = LocalDate.of(year, month, i);
            if(!map.containsKey(date)) {
                map.put(date, new GetAppointmentDateCount(date.toString(), 0));
            }
        }
        return map;
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
        return sortByType(
                new ArrayList<GetAppointmentDateCount>(fillMapWithAllMonths(countDict).values()));
    }

    HashMap<Month, GetAppointmentDateCount> fillMapWithAllMonths(HashMap<Month, GetAppointmentDateCount> map) {
        String[] monthsArr = new DateFormatSymbols().getMonths();
        String[] months = Arrays.copyOfRange(monthsArr, 0, monthsArr.length - 1);
        for(String month : months) {
            Month m = Month.valueOf(month.toUpperCase());
            if(!map.containsKey(m)) {
                map.put(m, new GetAppointmentDateCount(m.toString(), 0));
            }
        }
        return map;
    }

    private List<GetAppointmentDateCount> sortByType(List<GetAppointmentDateCount> finishedAppointments) {
        if(this.type.equals("year")) {
            finishedAppointments.sort(GetAppointmentDateCount.SortByMonth);
        }else {
            Collections.sort(finishedAppointments, Comparator.comparing(GetAppointmentDateCount::getDate));
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
