package org.medihub.application.ports.outgoing.clinic_room;


import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;

public interface DeleteClinicRoomPort {
    void deleteClinicRoom(Long id) throws NotFoundException, ForbiddenException;
}
