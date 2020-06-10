package org.medihub.application.ports.outgoing.leave_request;

import org.medihub.domain.LeaveRequest;
import org.medihub.domain.medical_doctor.MedicalDoctor;

public interface ApproveLeaveRequestPort {
    void approveLeaveRequest(LeaveRequest leaveRequest);
}
