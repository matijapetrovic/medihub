package org.medihub.application.services.leave_request.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.AddLeaveRequestUseCase;
import org.medihub.application.ports.incoming.leave_request.AddNurseLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.leave_request.AddNurseLeaveRequestPort;
import org.medihub.application.ports.outgoing.medical_nurse.GetMedicalNurseByAccountIdPort;
import org.medihub.domain.NurseLeaveRequest;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

@RequiredArgsConstructor
public class AddNurseLeaveRequestService implements AddNurseLeaveRequestUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final AddNurseLeaveRequestPort addNurseLeaveRequestPort;
    private final GetMedicalNurseByAccountIdPort getMedicalNurseByAccountIdPort;

    @Override
    public void addNurseLeaveRequest(AddLeaveRequestUseCase.AddLeaveCommand command) {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        addNurseLeaveRequestPort.addNurseLeaveRequest(new NurseLeaveRequest(
                null,
                command.getDates().get(0),
                command.getDates().get(1),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(command.getType()).get().toString(),
                getMedicalNurseByAccountIdPort.getMedicalNurseByAccountId(authenticated.getId())
        ));
    }
}
