package org.medihub.application.services.appointment_type.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment_type.DeleteAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.appointment_type.DeleteAppointmentTypePort;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class DeleteAppointmentTypeService implements DeleteAppointmentTypeUseCase {
    private final DeleteAppointmentTypePort deleteAppointmentTypePort;


    @Override
    @Transactional
    public void delete(Long id) throws NotFoundException, ForbiddenException {
        deleteAppointmentTypePort.delete(id);
    }
}
