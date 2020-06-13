package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
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
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Override
    public void addAppointment(AddAppointmentCommand addAppointmentCommand) {
        Appointment appointment = new Appointment(
                null,
                LocalDate.parse(addAppointmentCommand.getDate()),
                LocalTime.parse(addAppointmentCommand.getTime()),
                getPatientsPort.getPatientById(addAppointmentCommand.getPatientId()),
                getDoctorsPort.getMedicalDoctorById(addAppointmentCommand.getDoctorId()),
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId()),
                getPrice(addAppointmentCommand.getDoctorId())
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
    }

    private BigDecimal getPrice(Long doctorId) {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin admin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        Clinic clinic = loadClinicPort.loadClinic(admin.getClinic().getId());
        MedicalDoctor doctor = loadDoctorPort.loadDoctor(doctorId);

        return clinic.getAppointmentPrices().get(doctor.getSpecialization()).getAmount();
    }
}
