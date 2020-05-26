package org.medihub.application.ports.incoming.leave_request;

import org.medihub.domain.LeaveRequest;

import java.util.List;

public interface GetLeaveRequestUseCase {
    List<LeaveRequest> getAll();
}
