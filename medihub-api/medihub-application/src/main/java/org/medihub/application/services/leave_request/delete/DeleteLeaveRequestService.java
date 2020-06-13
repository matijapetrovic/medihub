package org.medihub.application.services.leave_request.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.DeleteLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.DeleteLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.domain.LeaveRequest;

@RequiredArgsConstructor
public class DeleteLeaveRequestService implements DeleteLeaveRequestUseCase {
    private final DeleteLeaveRequestPort deleteLeaveRequestPort;
    private final SendEmailPort sendEmailPort;
    private final GetLeaveRequestPort getLeaveRequestPort;

    @Override
    public void delete(Long id, String reason) {
        LeaveRequest leaveRequest = getLeaveRequestPort.getById(id);
        deleteLeaveRequestPort.delete(id);
        notifyMedicalDoctor(
                leaveRequest.getMedicalDoctor().getPersonalInfo().getAccount().getEmail(),
                reason);
    }

    private void notifyMedicalDoctor(String email, String reason) {
        String to = email;
        String subject = "Leave request rejected";
        String text = String.format("Your leave request has been rejected.\nReason: %s", reason);
        sendEmailPort.sendEmail(to, subject, text);
    }
}
