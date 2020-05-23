package org.medihub.application.services.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment_type.GetAppointmentTypesPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.predefined_appointment.AddPredefinedAppointmentPort;
import org.medihub.domain.appointment.PredefinedAppointment;

import java.time.LocalTime;

@RequiredArgsConstructor
public class AddPredefinedAppointmentService implements AddPredefinedAppointmentUseCase {
    private final AddPredefinedAppointmentPort addPredefinedAppointmentPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final GetAppointmentTypesPort getAppointmentTypesPort;

    @Override
    public void addPredefinedAppointment(AddPredefinedAppointmentCommand command) {
        PredefinedAppointment predefinedAppointment =
                new PredefinedAppointment(
                        null,
                        command.getDoctorId(),
                        LocalTime.parse(command.getStart()),
                        command.getDuration(),
                        getClinicRoomsPort.getClinicRoom(command.getClinicRoomId()),
                        getAppointmentTypesPort.getById(command.getAppointmentTypeId())
                );
        addPredefinedAppointmentPort.addPredefinedAppointment(predefinedAppointment);
    }
}
