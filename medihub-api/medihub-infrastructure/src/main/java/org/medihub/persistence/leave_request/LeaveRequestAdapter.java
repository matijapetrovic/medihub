package org.medihub.persistence.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.leave_request.AddLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.DeleteLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.medihub.domain.LeaveRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LeaveRequestAdapter implements
        AddLeaveRequestPort,
        GetLeaveRequestPort,
        DeleteLeaveRequestPort{
    private final LeaveRequestMapper leaveRequestMapper;
    private final LeaveRequestRepository leaveRequestRepository;

    @Override
    public void addLeave(LeaveRequest leaveRequest) {
        leaveRequestRepository.save(leaveRequestMapper.mapToJpaEntity(leaveRequest));
    }

    @Override
    public List<LeaveRequest> getAll() {
        return leaveRequestMapper.mapToDomainList(leaveRequestRepository.findAll());
    }

    @Override
    public LeaveRequest getById(Long id) {
        return leaveRequestMapper.mapToDomainEntity(leaveRequestRepository.findById(id).get());
    }

    @Override
    public void delete(Long id) {
        leaveRequestRepository.deleteById(id);
    }
}
