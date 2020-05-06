package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeSlot {
    int indexOfTerm;
    boolean scheduled;

    public void shedule(){
        this.scheduled = !this.scheduled;
    }
}
