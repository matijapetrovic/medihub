package org.medihub.persistence.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.leave_request.AddNurseLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.*;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.NurseLeaveRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LeaveRequestAdapter implements
        AddLeaveRequestPort,
        GetLeaveRequestPort,
        DeleteLeaveRequestPort,
        AddNurseLeaveRequestPort,
        GetNurseLeaveRequestsPort,
        DeleteNurseLeaveRequestPort,
        GetNurseLeaveRequestPort {
    private final LeaveRequestMapper leaveRequestMapper;
    private final LeaveRequestRepository leaveRequestRepository;
    private final NurseLeaveRequestRepository nurseLeaveRequestRepository;
    private final NurseLeaveRequestMapper nurseLeaveRequestMapper;

    @Override
    public void addLeave(LeaveRequest leaveRequest) {
        leaveRequestRepository.save(leaveRequestMapper.mapToJpaEntity(leaveRequest));
    }

    @Override
    public List<LeaveRequest> getAll(Long clinicId) {
        return leaveRequestMapper.mapToDomainList(leaveRequestRepository.findAllByDoctorClinicId(clinicId));
    }

    @Override
    public LeaveRequest getById(Long id) {
        return leaveRequestMapper.mapToDomainEntity(leaveRequestRepository.findById(id).get());
    }

    @Override
    public LeaveRequest getByIdWithLod(Long id) throws NotFoundException {
        Optional<LeaveRequestJpaEntity> leaveRequestJpaEntity = leaveRequestRepository.findByIdWithLock(id);
        if(!leaveRequestJpaEntity.isPresent())
            throw new NotFoundException();
        return leaveRequestMapper.mapToDomainEntity(leaveRequestJpaEntity.get());
    }

    @Override
    public void delete(Long id) {
        leaveRequestRepository.deleteById(id);
    }

    @Override
    public void addNurseLeaveRequest(NurseLeaveRequest nurseLeaveRequest) {
        nurseLeaveRequestRepository.save(nurseLeaveRequestMapper.mapToJpaEntity(nurseLeaveRequest));
    }

    @Override
    public List<NurseLeaveRequest> getNurseLeaveRequests() {
        return nurseLeaveRequestRepository
                .findAll()
                .stream()
                .map(nurseLeaveRequestMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNurseLeaveRequest(Long id) {
        nurseLeaveRequestRepository.deleteById(id);
    }

    @Override
    public NurseLeaveRequest getNurseLeaveRequest(Long id) throws NotFoundException {
        return nurseLeaveRequestMapper.mapToDomainEntity(nurseLeaveRequestRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
