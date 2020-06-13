package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.clinic_room.AddAppointmentToClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadClinicPort loadClinicPort;
    private final LoadDoctorPort loadDoctorPort;
    private final SendEmailPort sendEmailPort;

    @Override
    @Transactional
    public void addAppointment(AddAppointmentCommand addAppointmentCommand) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        AppointmentRequest appointmentRequest = loadAppointmentRequestPort.loadById(addAppointmentCommand.getId());

        if(!clinicAdmin.getClinic().getId().equals(appointmentRequest.getDoctor().getClinic().getId()))
            throw new ForbiddenException();

        Appointment appointment = new Appointment(
                null,
                    appointmentRequest.getDate(),
                appointmentRequest.getTime(),
                appointmentRequest.getPatient(),
                appointmentRequest.getDoctor(),
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId())
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
        deleteAppointmentRequestPort.deleteAppointmentRequest(appointmentRequest.getId());
    }
}
