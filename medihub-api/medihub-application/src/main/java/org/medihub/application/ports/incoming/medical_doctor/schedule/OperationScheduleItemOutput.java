package org.medihub.application.ports.incoming.medical_doctor.schedule;

import lombok.AllArgsConstructor;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.Operation;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OperationScheduleItemOutput extends DailyScheduleItemOutput {

    public DoctorOutput doctor;
    public List<DoctorOutput> presentDoctors;
    public AppointmentOutput operation;

    public OperationScheduleItemOutput(Long id,
                                       String time,
                                       String type,
                                       MedicalDoctor doctor,
                                       Set<MedicalDoctor> presentDoctors,
                                       Operation operation) {
        super(id, time, type);

        this.doctor = new DoctorOutput(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getLastName()
        );
        this.presentDoctors = presentDoctors
                .stream()
                .map(entity -> new DoctorOutput(
                        entity.getId(),
                        entity.getFirstName(),
                        entity.getLastName()
                ))
                .collect(Collectors.toList());
        this.operation = new AppointmentOutput(
                operation.getId(),
                operation.getDate().toString(),
                operation.getTime().toString(),
                new PatientOutput(operation.getPatient().getId(),
                        operation.getPatient().getPersonalInfo().getFirstName(),
                        operation.getPatient().getPersonalInfo().getLastName()),
                new DoctorOutput(operation.getDoctor().getId(),
                        operation.getDoctor().getPersonalInfo().getFirstName(),
                        operation.getDoctor().getPersonalInfo().getLastName()),
                new ClinicRoomOutput(operation.getClinicRoom().getId(),
                        operation.getClinicRoom().getName(),
                        operation.getClinicRoom().getNumber())
        );
    }
}
