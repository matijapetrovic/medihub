package org.medihub.application.ports.outgoing.leave_request;

import org.medihub.domain.LeaveRequest;

public interface AddLeaveRequestPort {
    void addLeave(LeaveRequest leaveRequest);
}
