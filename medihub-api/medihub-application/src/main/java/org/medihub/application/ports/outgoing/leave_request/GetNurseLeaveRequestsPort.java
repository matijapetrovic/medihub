package org.medihub.application.ports.outgoing.leave_request;

import org.medihub.domain.NurseLeaveRequest;

import java.util.List;

public interface GetNurseLeaveRequestsPort {
    List<NurseLeaveRequest> getNurseLeaveRequests();
}
