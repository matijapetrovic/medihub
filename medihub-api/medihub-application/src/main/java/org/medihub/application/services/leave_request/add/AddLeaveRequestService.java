package org.medihub.application.services.leave_request.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.AddLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.leave_request.AddLeaveRequestPort;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

@RequiredArgsConstructor
public class AddLeaveRequestService implements AddLeaveRequestUseCase {
    private final AddLeaveRequestPort addLeaveRequestPort;
    private final LoadDoctorPort loadDoctorPort;
    private final GetAuthenticatedPort getAuthenticatedPort;

    @Override
    public void addLeave(AddLeaveCommand addLeaveCommand) {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        addLeaveRequestPort.addLeave(
                new LeaveRequest(
                        null,
                        addLeaveCommand.getDates().get(0),
                        addLeaveCommand.getDates().get(1),
                        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(addLeaveCommand.getType()).get().toString(),
                        loadDoctorPort.loadDoctorByAccountId(authenticated.getId())
                        )
        );
    }
}
