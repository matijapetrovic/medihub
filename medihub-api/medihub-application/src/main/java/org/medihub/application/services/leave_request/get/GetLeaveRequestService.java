package org.medihub.application.services.leave_request.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.GetLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicAdmin;

import java.util.List;

@RequiredArgsConstructor
public class GetLeaveRequestService implements GetLeaveRequestUseCase {
    private final GetLeaveRequestPort getLeaveRequestPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<LeaveRequest> getAll() {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return getLeaveRequestPort.getAll(clinicAdmin.getClinic().getId());
    }
}
