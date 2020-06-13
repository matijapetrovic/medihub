package org.medihub.application.ports.incoming.leave_request;

public interface AddNurseLeaveRequestUseCase {
    void addNurseLeaveRequest(AddLeaveRequestUseCase.AddLeaveCommand command);
}
