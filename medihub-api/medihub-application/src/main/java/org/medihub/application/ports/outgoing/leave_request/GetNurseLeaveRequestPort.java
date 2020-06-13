package org.medihub.application.ports.outgoing.leave_request;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.NurseLeaveRequest;
import org.medihub.domain.medical_nurse.MedicalNurse;

public interface GetNurseLeaveRequestPort {
    NurseLeaveRequest getNurseLeaveRequest(Long id) throws NotFoundException;
}
