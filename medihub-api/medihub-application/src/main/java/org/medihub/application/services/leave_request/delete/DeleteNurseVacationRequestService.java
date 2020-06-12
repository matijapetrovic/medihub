package org.medihub.application.services.leave_request.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.DeleteNurseLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.DeleteNurseLeaveRequestPort;

@RequiredArgsConstructor
public class DeleteNurseVacationRequestService implements DeleteNurseLeaveRequestUseCase {
    private final DeleteNurseLeaveRequestPort deleteNurseLeaveRequestPort;

    @Override
    public void deleteNurseLeaveRequest(Long id) {
        deleteNurseLeaveRequestPort.deleteNurseLeaveRequest(id);
    }
}
