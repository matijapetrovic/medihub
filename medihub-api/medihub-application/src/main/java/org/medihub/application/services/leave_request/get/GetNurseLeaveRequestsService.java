package org.medihub.application.services.leave_request.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.GetNurseLeaveRequestsQuery;
import org.medihub.application.ports.incoming.leave_request.NurseLeaveRequestOutput;
import org.medihub.application.ports.outgoing.leave_request.GetNurseLeaveRequestsPort;
import org.medihub.domain.NurseLeaveRequest;

import java.util.List;

@RequiredArgsConstructor
public class GetNurseLeaveRequestsService implements GetNurseLeaveRequestsQuery {
    private final GetNurseLeaveRequestsPort getNurseLeaveRequestsPort;

    @Override
    public List<NurseLeaveRequestOutput> getNurseLeaveRequests() {
        List<NurseLeaveRequest> requests = getNurseLeaveRequestsPort.getNurseLeaveRequests();
    }
}
