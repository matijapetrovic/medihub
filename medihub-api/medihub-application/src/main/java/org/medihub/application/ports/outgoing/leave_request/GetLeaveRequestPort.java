package org.medihub.application.ports.outgoing.leave_request;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.LeaveRequest;

import java.util.List;

public interface GetLeaveRequestPort {
    List<LeaveRequest> getAll(Long clinicId);
    LeaveRequest getById(Long id);
    LeaveRequest getByIdWithLod(Long id) throws NotFoundException;
}
