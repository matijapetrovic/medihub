package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.clinic_room.AddAppointmentToClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.domain.appointment.Appointment;

import java.io.NotActiveException;
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

    @Override
    public void addAppointment(AddAppointmentCommand addAppointmentCommand) throws NotFoundException, NotAvailableException, NotActiveException {
        Appointment appointment = new Appointment(
                null,
                LocalDate.parse(addAppointmentCommand.getDate()),
                LocalTime.parse(addAppointmentCommand.getTime()),
                getPatientsPort.getPatientById(addAppointmentCommand.getPatientId()),
                getDoctorsPort.getMedicalDoctorById(addAppointmentCommand.getDoctorId()),
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId())
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
}
