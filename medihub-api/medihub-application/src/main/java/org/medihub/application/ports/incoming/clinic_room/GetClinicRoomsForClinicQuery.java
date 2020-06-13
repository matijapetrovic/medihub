package org.medihub.application.ports.incoming.clinic_room;

import java.util.List;

public interface GetClinicRoomsForClinicQuery {
    List<GetClinicRoomsOutput> getClinicRooms(Long clinicId);
}
