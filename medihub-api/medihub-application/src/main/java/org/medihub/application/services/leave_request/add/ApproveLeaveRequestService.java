package org.medihub.application.services.leave_request.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.ApproveLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.leave_request.ApproveLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.DeleteLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalDoctorScheduleItemPort;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class ApproveLeaveRequestService implements ApproveLeaveRequestUseCase {
    private final ApproveLeaveRequestPort approveLeaveRequestPort;
    private final GetLeaveRequestPort getLeaveRequestPort;
    private final DeleteLeaveRequestPort deleteLeaveRequestPort;
    private final SendEmailPort sendEmailPort;
    private final GetDoctorsPort getDoctorsPort;

    @Override
    @Transactional
    public void approveLeaveRequest(Long id, Long medicalDoctorId) {
        approveLeaveRequestPort.approveLeaveRequest(getLeaveRequestPort.getById(id));
        deleteLeaveRequestPort.delete(id);

        MedicalDoctor doctor = getDoctorsPort.getMedicalDoctorById(medicalDoctorId);
        notifyMedicalDoctor(doctor);
    }

    private void notifyMedicalDoctor(MedicalDoctor medicalDoctor) {
        String to = medicalDoctor.getPersonalInfo().getAccount().getEmail();
        String subject = "Leave request accepted";
        String text = String.format("Your leave request has been accepted.");
        sendEmailPort.sendEmail(to, subject, text);
    }
}
