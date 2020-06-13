package org.medihub.application.ports.incoming.leave_request;

import java.util.List;

public interface GetNurseLeaveRequestsQuery {
    List<NurseLeaveRequestOutput> getNurseLeaveRequests();
}
