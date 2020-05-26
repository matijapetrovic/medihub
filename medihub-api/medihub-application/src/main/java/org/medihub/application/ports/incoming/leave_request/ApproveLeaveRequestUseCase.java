package org.medihub.application.ports.incoming.leave_request;

public interface ApproveLeaveRequestUseCase {
    void approveLeaveRequest(Long id, Long doctorId);
}
