package org.medihub.application.services.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.GetLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.medihub.domain.LeaveRequest;

import java.util.List;

@RequiredArgsConstructor
public class GetLeaveRequestService implements GetLeaveRequestUseCase {
    private final GetLeaveRequestPort getLeaveRequestPort;

    @Override
    public List<LeaveRequest> getAll() {
        return getLeaveRequestPort.getAll();
    }
}
