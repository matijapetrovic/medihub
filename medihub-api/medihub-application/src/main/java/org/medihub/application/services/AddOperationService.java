package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.operation.AddOperationUseCase;
import org.medihub.application.ports.incoming.operation.OperationOutput;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalDoctorScheduleItemPort;
import org.medihub.domain.appointment.Operation;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorOperationScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AddOperationService implements AddOperationUseCase {
    private final GetDoctorsPort getDoctorsPort;
    private final GetPatientsPort getPatientsPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final SaveMedicalDoctorScheduleItemPort saveMedicalDoctorScheduleItemPort;

    @Override
    public OperationOutput addOperation(AddOperationCommand command) throws NotFoundException {
        //create operation
        Operation operation = new Operation(
                null,
                getPatientsPort.getPatientById(command.getPatientId()),
                getDoctorsPort.getMedicalDoctorById(command.getDoctorId()),
                LocalDate.parse(command.getDate()),
                LocalTime.parse(command.getTime()),
                getClinicRoomsPort.getClinicRoomById(command.getClinicRoomId()),
                createDoctorList(command.getPresentDoctors())
        );

        //save operation
        Operation savedOperation = (Operation) saveAppointmentPort.saveAppointment(operation);

        //Create main doctor schedule item
        MedicalDoctor mainDoctor = getDoctorsPort.getMedicalDoctorById(command.getDoctorId());
        MedicalDoctorOperationScheduleItem mainScheduleItem = new MedicalDoctorOperationScheduleItem(
                null,
                LocalTime.parse(command.getTime()),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.OPERATION,
                savedOperation
        );

        saveMedicalDoctorScheduleItemPort.saveMedicalDoctorScheduleItem(mainScheduleItem,
                mainDoctor,
                LocalDate.parse(command.getDate()) );

        //Create present doctor schedule items
        for(Long doctorId : command.getPresentDoctors()) {
            MedicalDoctor doctor = getDoctorsPort.getMedicalDoctorById(doctorId);
            MedicalDoctorOperationScheduleItem operationScheduleItem = new MedicalDoctorOperationScheduleItem(
                    null,
                    LocalTime.parse(command.getTime()),
                    MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.OPERATION,
                    savedOperation
            );
            saveMedicalDoctorScheduleItemPort.saveMedicalDoctorScheduleItem(operationScheduleItem,
                    doctor,
                    LocalDate.parse(command.getDate()) );
        }

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
                .map(entry -> entry.getId())
                .collect(Collectors.toList());
    }

    private Set<MedicalDoctor> createDoctorList(List<Long> ids) {
        Set<MedicalDoctor> doctors = new HashSet<MedicalDoctor>();

        for(Long id : ids) {
            doctors.add(getDoctorsPort.getMedicalDoctorById(id));
        }

        return doctors;
    }
}
