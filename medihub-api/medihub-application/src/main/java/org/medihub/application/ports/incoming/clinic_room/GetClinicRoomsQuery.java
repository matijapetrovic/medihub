package org.medihub.application.ports.incoming.clinic_room;

import java.util.List;

public interface GetClinicRoomsQuery {
    List<GetClinicRoomsOutput> getClinicRooms();
}
