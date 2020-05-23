package org.medihub.persistence.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.LeaveRequest;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LeaveRequestMapper {
    private final MedicalDoctorMapper medicalDoctorMapper;

    public LeaveRequestJpaEntity mapToJpaEntity(LeaveRequest leaveRequest) {
        return new LeaveRequestJpaEntity(
            leaveRequest.getId(),
                leaveRequest.getDates().stream().map(date -> Date.valueOf(date)).collect(Collectors.toList()),
                medicalDoctorMapper.mapToJpaEntity(leaveRequest.getMedicalDoctor()),
                leaveRequest.getType()
        );
    }

    public LeaveRequest mapToDomainEntity(LeaveRequestJpaEntity leaveRequest) {
        return new LeaveRequest(
                leaveRequest.getId(),
                leaveRequest.getDates().stream().map(date -> date.toString()).collect(Collectors.toList()),
                leaveRequest.getType(),
                medicalDoctorMapper.mapToDomainEntity(leaveRequest.getDoctor())
        );
    }

    public List<LeaveRequest> mapToDomainList(List<LeaveRequestJpaEntity> leaveRequestJpaEntities) {
        return leaveRequestJpaEntities
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
