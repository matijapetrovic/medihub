package org.medihub.application.ports.outgoing;

import org.medihub.domain.ClinicRoom;

public interface LoadClinicRoomPort {
    ClinicRoom loadClinicRoom(String name);
}
