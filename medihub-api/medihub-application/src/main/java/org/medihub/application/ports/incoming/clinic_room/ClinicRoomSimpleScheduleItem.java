package org.medihub.application.ports.incoming.clinic_room;

import lombok.Value;;

@Value
public class ClinicRoomSimpleScheduleItem {
    private String date;
    private String time;
}
