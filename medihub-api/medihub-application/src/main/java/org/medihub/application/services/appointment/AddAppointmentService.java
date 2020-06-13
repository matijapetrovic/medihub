package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
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
    private final SaveAppointmentPort saveAppointmentPort;
    private final GetDoctorsPort getDoctorsPort;
    private final GetPatientsPort getPatientsPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final AddAppointmentToMedicalDoctorSchedulePort addAppointmentToMedicalDoctorSchedulePort;
    private final AddAppointmentToClinicRoomPort addAppointmentToClinicRoomPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadClinicPort loadClinicPort;
    private final LoadDoctorPort loadDoctorPort;
    private final SendEmailPort sendEmailPort;

    @Override
    public void addAppointment(AddAppointmentCommand addAppointmentCommand) throws NotFoundException, NotAvailableException {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin admin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        Appointment appointment = new Appointment(
                null,
                LocalDate.parse(addAppointmentCommand.getDate()),
                LocalTime.parse(addAppointmentCommand.getTime()),
                getPatientsPort.getPatientById(addAppointmentCommand.getPatientId()),
                getDoctorsPort.getMedicalDoctorById(addAppointmentCommand.getDoctorId()),
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId()),
                getPrice(addAppointmentCommand.getDoctorId(), admin)
        );
        appointment = saveAppointmentPort.saveAppointment(appointment);
        addAppointmentToClinicRoomPort.addAppointmentToClinicRoom(
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId()),
                LocalDate.parse(addAppointmentCommand.getDate()),
                LocalTime.parse(addAppointmentCommand.getTime())
        );
        addAppointmentToMedicalDoctorSchedulePort.addAppointmentToSchedule(
                LocalDate.parse(addAppointmentCommand.getDate()),
                LocalTime.parse(addAppointmentCommand.getTime()),
                appointment);
        this.notifyClinicAdmin(appointment, admin);
    }

    private BigDecimal getPrice(Long doctorId, ClinicAdmin admin) {
        Clinic clinic = loadClinicPort.loadClinic(admin.getClinic().getId());
        MedicalDoctor doctor = loadDoctorPort.loadDoctor(doctorId);

        return clinic.getAppointmentPrices().get(doctor.getSpecialization()).getAmount();
    }

    private void notifyClinicAdmin(Appointment appointment, ClinicAdmin admin) {
        String to = admin.getPersonalInfo().getAccount().getEmail();
        String subject = "Appointment request notification";
        String text = String.format("Doctor %s has been scheduled request an appointment with %s at %s",
                appointment.getDoctor().getFullName(),
                appointment.getPatient().getFullName(),
                LocalDateTime.of(appointment.getDate(), appointment.getTime()));
        sendEmailPort.sendEmail(to, subject, text);
    }
}
