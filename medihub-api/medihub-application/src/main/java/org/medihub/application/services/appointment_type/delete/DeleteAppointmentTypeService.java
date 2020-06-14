package org.medihub.application.services.appointment_type.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment_type.DeleteAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.appointment_type.DeleteAppointmentTypePort;
import org.medihub.application.ports.outgoing.doctor.CountNumberOfDoctorsWithApppointmentTypePort;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class DeleteAppointmentTypeService implements DeleteAppointmentTypeUseCase {
    private final DeleteAppointmentTypePort deleteAppointmentTypePort;
    private final CountNumberOfDoctorsWithApppointmentTypePort countNumberOfDoctorsWithApppointmentType;


    @Override
    @Transactional
    public void delete(Long id) throws NotFoundException, ForbiddenException {
        if(countNumberOfDoctorsWithApppointmentType.countNumber(id) > 0)
            throw new ForbiddenException("Can not delete this appointment type");
        deleteAppointmentTypePort.delete(id);
    }
}
