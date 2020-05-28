package org.medihub.application.services.leave_request.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.DeleteLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.DeleteLeaveRequestPort;

@RequiredArgsConstructor
public class DeleteLeaveRequestService implements DeleteLeaveRequestUseCase {
    private final DeleteLeaveRequestPort deleteLeaveRequestPort;

    @Override
    public void delete(Long id) {
        deleteLeaveRequestPort.delete(id);
    }
}
