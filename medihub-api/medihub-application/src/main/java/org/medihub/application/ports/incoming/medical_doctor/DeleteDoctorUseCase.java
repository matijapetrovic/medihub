package org.medihub.application.ports.incoming.medical_doctor;

import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;

import javax.validation.constraints.NotNull;

public interface DeleteDoctorUseCase {
    void deleteDoctor(@NotNull Long doctorId) throws ForbiddenException, NotFoundException;
}
