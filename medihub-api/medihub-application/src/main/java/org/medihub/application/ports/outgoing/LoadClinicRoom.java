package org.medihub.application.ports.outgoing;

import org.medihub.domain.ClinicRoom;

public interface LoadClinicRoom {
    ClinicRoom loadClinicRoom(String name);
}
