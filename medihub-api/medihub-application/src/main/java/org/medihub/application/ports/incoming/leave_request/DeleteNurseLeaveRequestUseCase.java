package org.medihub.application.ports.incoming.leave_request;

import org.medihub.application.exceptions.NotFoundException;

public interface DeleteNurseLeaveRequestUseCase {
    void deleteNurseLeaveRequest(Long id, String reason) throws NotFoundException;
}
