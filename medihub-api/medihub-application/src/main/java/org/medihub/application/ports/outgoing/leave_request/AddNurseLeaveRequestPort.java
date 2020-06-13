package org.medihub.application.ports.outgoing.leave_request;

import org.medihub.domain.NurseLeaveRequest;

public interface AddNurseLeaveRequestPort {
    void addNurseLeaveRequest(NurseLeaveRequest nurseLeaveRequest);
}
