package org.medihub.persistence.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.NurseLeaveRequest;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.persistence.medical_nurse.MedicalNurseMapper;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.sql.Date;

@Component
@RequiredArgsConstructor
public class NurseLeaveRequestMapper {
    private final MedicalNurseMapper medicalNurseMapper;

    public NurseLeaveRequestJpaEntity mapToJpaEntity(NurseLeaveRequest leaveRequest) {
        return new NurseLeaveRequestJpaEntity(
                leaveRequest.getId(),
                Date.valueOf(leaveRequest.getStart()),
                Date.valueOf(leaveRequest.getEnd()),
                medicalNurseMapper.mapToJpaEntity(leaveRequest.getMedicalNurse()),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(leaveRequest.getType().toUpperCase()).getOrdinal()
        );
    }

    public NurseLeaveRequest mapToDomainEntity(NurseLeaveRequestJpaEntity leaveRequest) {
        return new NurseLeaveRequest(
                leaveRequest.getId(),
                leaveRequest.getStartDate().toLocalDate(),
                leaveRequest.getEndDate().toLocalDate(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(leaveRequest.getType()).get().toString(),
                medicalNurseMapper.mapToDomainEntity(leaveRequest.getNurse())
        );
    }
}
