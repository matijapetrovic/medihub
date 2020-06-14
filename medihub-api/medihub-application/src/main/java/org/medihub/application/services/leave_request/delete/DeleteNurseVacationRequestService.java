package org.medihub.application.services.leave_request.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.leave_request.DeleteNurseLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.DeleteNurseLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetNurseLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetNurseLeaveRequestsPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.NurseLeaveRequest;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_nurse.MedicalNurse;
import org.medihub.domain.medical_nurse.MedicalNurseVacationScheduleItem;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class DeleteNurseVacationRequestService implements DeleteNurseLeaveRequestUseCase {
    private final DeleteNurseLeaveRequestPort deleteNurseLeaveRequestPort;
    private final SendEmailPort sendEmailPort;
    private final GetNurseLeaveRequestPort getNurseLeaveRequestPort;

    @Override
    public void deleteNurseLeaveRequest(Long id, String reason) throws NotFoundException {
        NurseLeaveRequest leave = getNurseLeaveRequestPort.getNurseLeaveRequest(id);
        deleteNurseLeaveRequestPort.deleteNurseLeaveRequest(id);

        notifyNurse(leave.getMedicalNurse(), reason);
    }

    private void notifyNurse(MedicalNurse nurse, String description) {
        String to = nurse.getPersonalInfo().getAccount().getEmail();
        String subject = "Vacation request rejection";
        sendEmailPort.sendEmail(to, subject, description);
    }
}
