package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.operation.AddOperationUseCase;
import org.medihub.application.ports.incoming.operation.OperationOutput;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.LoadAppointmentRequestPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalDoctorScheduleItemPort;
import org.medihub.domain.MedicalStaff;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.appointment.Operation;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.patient.Patient;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AddOperationService implements AddOperationUseCase {
    private final GetDoctorsPort getDoctorsPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final SaveMedicalDoctorScheduleItemPort saveMedicalDoctorScheduleItemPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadAppointmentRequestPort loadAppointmentRequestPort;
    private final DeleteAppointmentRequestPort deleteAppointmentRequestPort;
    private final SendEmailPort sendEmail;

    @Override
    @Transactional
    public OperationOutput addOperation(AddOperationCommand command) throws NotFoundException, ForbiddenException, NotAvailableException {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin admin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());

        AppointmentRequest appointmentRequest = loadAppointmentRequestPort.loadById(command.getRequestId());
        if(!admin.getClinic().getId().equals(appointmentRequest.getDoctor().getClinic().getId()))
            throw new ForbiddenException();

        MedicalDoctor doctor = appointmentRequest.getDoctor().getId().equals(command.getDoctorId()) ?
                appointmentRequest.getDoctor() : getDoctorsPort.getMedicalDoctorById(command.getDoctorId());
        if (!admin.getClinic().getId().equals(doctor.getClinic().getId()))
            throw new ForbiddenException();

        Set<MedicalDoctor> presentDoctors = new LinkedHashSet<>();

        for(Long doctorId : command.getPresentDoctors()) {
            MedicalDoctor tempDoctor = getDoctorsPort.getMedicalDoctorById(doctorId);
            if(!admin.getClinic().getId().equals(tempDoctor.getClinic().getId()))
                throw new ForbiddenException();
            presentDoctors.add(tempDoctor);
        }

        ClinicRoom tempRoom = getClinicRoomsPort.getClinicRoomById(command.getClinicRoomId());

        if(!admin.getClinic().getId().equals(tempRoom.getClinic().getId()))
            throw new ForbiddenException();

        Operation operation = new Operation(
                null,
                appointmentRequest.getPatient(),
                doctor,
                command.getDate(),
                command.getTime(),
                tempRoom,
                presentDoctors,
                appointmentRequest.getPrice().getAmount()
        );

        //save operation
        Operation savedOperation = (Operation) saveAppointmentPort.saveAppointment(operation);

        //Create main doctor schedule item
        MedicalDoctorAppointmentScheduleItem mainScheduleItem = new MedicalDoctorAppointmentScheduleItem(
                null,
                operation.getTime(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.OPERATION,
                savedOperation
        );

        saveMedicalDoctorScheduleItemPort.saveMedicalDoctorScheduleItem(mainScheduleItem,
                operation.getDoctor(),
                operation.getDate());

        //Create present doctor schedule items
        for(MedicalDoctor doc : presentDoctors) {
            MedicalDoctorAppointmentScheduleItem operationScheduleItem = new MedicalDoctorAppointmentScheduleItem(
                    null,
                    operation.getTime(),
                    MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.OPERATION,
                    savedOperation
            );
            saveMedicalDoctorScheduleItemPort.saveMedicalDoctorScheduleItem(operationScheduleItem,
                    doc,
                    operation.getDate() );
        }

        deleteAppointmentRequestPort.deleteAppointmentRequest(appointmentRequest.getId());

        notifyDoctor(savedOperation, savedOperation.getDoctor());
        notifyPatient(savedOperation, savedOperation.getPatient());
        for(MedicalDoctor doc : presentDoctors)
            notifyDoctor(savedOperation, doc);

        return createOutput(savedOperation);
    }

    private OperationOutput createOutput(Operation operation) {
        return new OperationOutput(
                operation.getId(),
                operation.getDoctor().getSpecialization().getName(),
                operation.getDoctor().getFullName(),
                operation.getDoctor().getClinic().getName(),
                operation.getDate().toString(),
                createDoctorIdList(operation.getPresentDoctors()));
    }

    private List<Long> createDoctorIdList(Set<MedicalDoctor> doctors) {
        return doctors
                .stream()
                .map(MedicalStaff::getId)
                .collect(Collectors.toList());
    }

    private void notifyDoctor(Operation operation, MedicalDoctor doctor) {
        String to = doctor.getPersonalInfo().getAccount().getEmail();
        String subject = "Operation request notification";
        String text = String.format("Doctor %s has been scheduled request operation with %s at %s",
                doctor.getFullName(),
                operation.getPatient().getFullName(),
                LocalDateTime.of(operation.getDate(), operation.getTime()));
        sendEmail.sendEmail(to, subject, text);
    }

    private void notifyPatient(Operation operation, Patient patient) {
        String to = patient.getPersonalInfo().getAccount().getEmail();
        String subject = "Operation notification";
        String text = String.format("Patient %s has been scheduled operation with doctor %s at %s",
                patient.getFullName(),
                operation.getDoctor().getFullName(),
                LocalDateTime.of(operation.getDate(), operation.getTime()));
        sendEmail.sendEmail(to, subject, text);
    }
}
