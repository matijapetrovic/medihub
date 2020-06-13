package org.medihub.application.services.appointment;
import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.LoadAppointmentRequestPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic_room.AddAppointmentToClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.clinic.ClinicAdmin;

import javax.transaction.Transactional;
import java.io.NotActiveException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AddAppointmentService implements AddAppointmentUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadAppointmentRequestPort loadAppointmentRequestPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final DeleteAppointmentRequestPort deleteAppointmentRequestPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final AddAppointmentToMedicalDoctorSchedulePort addAppointmentToMedicalDoctorSchedulePort;
    private final AddAppointmentToClinicRoomPort addAppointmentToClinicRoomPort;
    private final SendEmailPort sendEmailPort;

    @Override
    @Transactional
    public void addAppointment(AddAppointmentCommand addAppointmentCommand) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        AppointmentRequest appointmentRequest = loadAppointmentRequestPort.loadByIdWithLock(addAppointmentCommand.getId());

        if(!clinicAdmin.getClinic().getId().equals(appointmentRequest.getDoctor().getClinic().getId()))
            throw new ForbiddenException();

        deleteAppointmentRequestPort.deleteAppointmentRequest(appointmentRequest.getId());

        Appointment appointment = new Appointment(
                null,
                appointmentRequest.getDate(),
                appointmentRequest.getTime(),
                appointmentRequest.getPatient(),
                appointmentRequest.getDoctor(),
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId()),
                appointmentRequest.getPrice().getAmount()
        );
        appointment = saveAppointmentPort.saveAppointment(appointment);
        addAppointmentToClinicRoomPort.addAppointmentToClinicRoom(
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId()),
                appointmentRequest.getDate(),
                appointmentRequest.getTime()
        );
        addAppointmentToMedicalDoctorSchedulePort.addAppointmentToSchedule(
                appointmentRequest.getDate(),
                appointmentRequest.getTime(),
                appointment);

        notifyClinicAdmin(appointment, clinicAdmin);
    }

    private void notifyClinicAdmin(Appointment appointment, ClinicAdmin admin) {
        String to = admin.getPersonalInfo().getAccount().getEmail();
        String subject = "Appointment request notification";
        String text = String.format("Doctor %s has been scheduled request appointment with %s at %s",
                appointment.getDoctor().getFullName(),
                appointment.getPatient().getFullName(),
                LocalDateTime.of(appointment.getDate(), appointment.getTime()));
        sendEmailPort.sendEmail(to, subject, text);
    }
}