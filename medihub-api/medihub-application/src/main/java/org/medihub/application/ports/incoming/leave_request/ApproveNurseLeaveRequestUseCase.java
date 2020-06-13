package org.medihub.application.ports.incoming.leave_request;

import lombok.Value;
import org.medihub.application.exceptions.NotFoundException;

public interface ApproveNurseLeaveRequestUseCase {
    void approve(ApproveNurseLeaveRequestCommand command) throws NotFoundException;

    @Value
    class ApproveNurseLeaveRequestCommand {
        Long id;
        Long nurse;
    }
}
