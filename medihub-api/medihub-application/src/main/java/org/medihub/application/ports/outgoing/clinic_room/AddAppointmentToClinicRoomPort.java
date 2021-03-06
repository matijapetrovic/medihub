package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.clinic_room.ClinicRoom;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AddAppointmentToClinicRoomPort {
    void addAppointmentToClinicRoom(ClinicRoom clinicRoom, LocalDate date, LocalTime time) throws NotFoundException, NotAvailableException;
}
