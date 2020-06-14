package org.medihub.application.ports.incoming.scheduling;

import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;

import java.io.NotActiveException;

public interface AutomaticSchedulingUseCase {
    void runAutomaticScheduleRoom() throws NotActiveException, ForbiddenException, NotFoundException, NotAvailableException;
}
