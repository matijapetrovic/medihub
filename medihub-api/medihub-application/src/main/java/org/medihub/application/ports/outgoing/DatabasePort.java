package org.medihub.application.ports.outgoing;

public interface DatabasePort {
    void addClinicRoom(String clinicRoom); // before we have model, it is string
}
