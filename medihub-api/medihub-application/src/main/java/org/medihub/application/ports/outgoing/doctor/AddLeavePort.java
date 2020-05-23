package org.medihub.application.ports.outgoing.doctor;

import org.medihub.domain.LeaveRequest;

public interface AddLeavePort {
    void addLeave(LeaveRequest leaveRequest);
}
