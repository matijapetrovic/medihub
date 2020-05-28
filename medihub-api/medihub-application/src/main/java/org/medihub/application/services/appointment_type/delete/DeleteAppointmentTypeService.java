package org.medihub.application.services.appointment_type.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.DeleteAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.appointment_type.DeleteAppointmentTypePort;

@RequiredArgsConstructor
public class DeleteAppointmentTypeService implements DeleteAppointmentTypeUseCase {
    private final DeleteAppointmentTypePort deleteAppointmentTypePort;


    @Override
    public void delete(Long id) {
        deleteAppointmentTypePort.delete(id);
    }
}
