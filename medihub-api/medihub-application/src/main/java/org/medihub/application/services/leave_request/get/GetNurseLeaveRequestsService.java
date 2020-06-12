package org.medihub.application.services.leave_request.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.GetNurseLeaveRequestsQuery;
import org.medihub.application.ports.incoming.leave_request.NurseLeaveRequestOutput;
import org.medihub.application.ports.outgoing.leave_request.GetNurseLeaveRequestsPort;
import org.medihub.domain.NurseLeaveRequest;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetNurseLeaveRequestsService implements GetNurseLeaveRequestsQuery {
    private final GetNurseLeaveRequestsPort getNurseLeaveRequestsPort;

    @Override
    public List<NurseLeaveRequestOutput> getNurseLeaveRequests() {
        List<NurseLeaveRequest> requests = getNurseLeaveRequestsPort.getNurseLeaveRequests();
        return createOutput(requests);
    }

    private List<NurseLeaveRequestOutput> createOutput(List<NurseLeaveRequest> requests) {
        return requests
                .stream()
                .map(entity -> new NurseLeaveRequestOutput(
                      entity.getId(),
                      entity.getStart().toString(),
                      entity.getEnd().toString(),
                      entity.getType(),
                      entity.getMedicalNurse().getAccount().getEmail(),
                      entity.getMedicalNurse().getId()
                ))
                .collect(Collectors.toList());
    }
}
