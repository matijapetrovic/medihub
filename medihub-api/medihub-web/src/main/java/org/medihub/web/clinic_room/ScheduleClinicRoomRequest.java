package org.medihub.web.clinic_room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleClinicRoomRequest {
    private Long id;
    private String date;
    private String time;
}
