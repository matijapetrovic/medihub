package org.medihub.persistence.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LeaveRequestMapper {
    private final MedicalDoctorMapper medicalDoctorMapper;

    public LeaveRequestJpaEntity mapToJpaEntity(LeaveRequest leaveRequest) {
        Timestamp dateStart = (leaveRequest.getStart() == null ? null : Timestamp.valueOf(LocalDateTime.of(leaveRequest.getStart(), LocalTime.MIDNIGHT)));
        Timestamp dateEnd = (leaveRequest.getEnd() == null ? null :Timestamp.valueOf(LocalDateTime.of(leaveRequest.getEnd().plusDays(1), LocalTime.MIDNIGHT)));
        return new LeaveRequestJpaEntity(
                leaveRequest.getId(),
                dateStart,
                dateEnd,
                medicalDoctorMapper.mapToJpaEntity(leaveRequest.getMedicalDoctor()),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(leaveRequest.getType().toUpperCase()).getOrdinal()
        );
    }

    public LeaveRequest mapToDomainEntity(LeaveRequestJpaEntity leaveRequest) {
        return new LeaveRequest(
                leaveRequest.getId(),
                leaveRequest.getStartDate().toLocalDateTime().toLocalDate(),
                leaveRequest.getEndDate().toLocalDateTime().toLocalDate(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(leaveRequest.getType()).get().toString(),
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
