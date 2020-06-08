package org.medihub.application.services.leave_request.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.ApproveLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.appointment.GetAppointmentPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.leave_request.ApproveLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.DeleteLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.medihub.domain.LeaveRequest;

@RequiredArgsConstructor
public class ApproveLeaveRequestService implements ApproveLeaveRequestUseCase {
    private final ApproveLeaveRequestPort approveLeaveRequestPort;
    private final GetLeaveRequestPort getLeaveRequestPort;
    private final DeleteLeaveRequestPort deleteLeaveRequestPort;

    @Override
    public void approveLeaveRequest(Long id, Long medicalDoctorId) {
        approveLeaveRequestPort.approveLeaveRequest(
                getLeaveRequestPort.getById(id));
        deleteLeaveRequestPort.delete(id);
    }
}
