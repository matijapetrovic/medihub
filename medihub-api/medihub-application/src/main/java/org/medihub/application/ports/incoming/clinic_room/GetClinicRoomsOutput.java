package org.medihub.application.ports.incoming.clinic_room;

import lombok.Value;

@Value
public class GetClinicRoomsOutput {
    Long id;
    String name;
}
