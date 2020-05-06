package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkingTime {

    private LocalTime from;
    private LocalTime to;
    private List<TimeSlot> timeSlots;

    public WorkingTime(String from, String to){
        this.from = LocalTime.parse(from);
        this.to =  LocalTime.parse(to);
    }

    public WorkingTime(LocalTime from, LocalTime to){
        this.from = from;
        this.to =  to;
    }

    private long getWorkingTimeDuration(){
        return Math.abs(HOURS.between(from, to));
    }

    private boolean isTermScheduled(int index){
        return timeSlots.get(index).isScheduled();
    }

    private List<String> getAllTerms(){
        return new ArrayList<>();
    }

    private void setTimeSlots(){

    }

}
