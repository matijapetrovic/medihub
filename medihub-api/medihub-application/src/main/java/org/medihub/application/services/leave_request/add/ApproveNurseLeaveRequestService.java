package org.medihub.application.services.leave_request.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.leave_request.ApproveNurseLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.DeleteNurseLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetNurseLeaveRequestPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalNurseScheduleItemPort;
import org.medihub.domain.NurseLeaveRequest;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.medical_nurse.MedicalNurseVacationScheduleItem;

@RequiredArgsConstructor
public class ApproveNurseLeaveRequestService implements ApproveNurseLeaveRequestUseCase {
    private final DeleteNurseLeaveRequestPort deleteNurseLeaveRequestPort;
    private final GetNurseLeaveRequestPort getNurseLeaveRequestPort;
    private final SaveMedicalNurseScheduleItemPort saveMedicalNurseScheduleItemPort;

    @Override
    public void approve(ApproveNurseLeaveRequestCommand command) throws NotFoundException {
        NurseLeaveRequest nurseLeaveRequest = getNurseLeaveRequestPort.getNurseLeaveRequest(command.getId());

        MedicalNurseVacationScheduleItem item = new MedicalNurseVacationScheduleItem(
                null,
                nurseLeaveRequest.getStart().atStartOfDay().toLocalTime(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(nurseLeaveRequest.getType()),
                nurseLeaveRequest.getEnd()
        );

        saveMedicalNurseScheduleItemPort.saveMedicalNurseScheduleItem(item,
                nurseLeaveRequest.getMedicalNurse(),
                nurseLeaveRequest.getStart());

        deleteNurseLeaveRequestPort.deleteNurseLeaveRequest(nurseLeaveRequest.getId());
    }
}
