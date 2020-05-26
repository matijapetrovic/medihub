package org.medihub.application.ports.outgoing.leave_request;

import org.medihub.domain.LeaveRequest;

import java.util.List;

public interface GetLeaveRequestPort {
    List<LeaveRequest> getAll();

    LeaveRequest getById(Long id);
}
