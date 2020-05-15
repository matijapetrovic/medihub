package org.medihub.domain.clinic_room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.scheduling.Schedule;

@AllArgsConstructor
@Getter
public class ClinicRoom {
    private Long id;
    private String name;
    private int number;
    private Clinic clinic;
}
