package org.medihub.application.ports.outgoing;

import org.medihub.domain.ClinicRoom;

public interface SaveClinicPort {
    void saveClinicRoom(ClinicRoom clinicRoom);
}
