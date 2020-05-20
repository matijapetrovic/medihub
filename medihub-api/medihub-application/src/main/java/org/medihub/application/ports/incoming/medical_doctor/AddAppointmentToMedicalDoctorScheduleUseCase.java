package org.medihub.application.ports.incoming.medical_doctor;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface AddAppointmentToMedicalDoctorScheduleUseCase {
    void addAppointmentToMedicalDoctorSchedule(AddScheduleCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddScheduleCommand extends SelfValidating<AddAppointmentToMedicalDoctorScheduleUseCase.AddScheduleCommand> {
        @NotBlank
        String date;
        @NotBlank
        String time;
        @NotNull
        Long patientId;
        @NotNull
        Long doctorId;
        @NotNull
        Long clinicRoomId;

        public AddScheduleCommand(
                String date,
                String time,
                Long patientId,
                Long doctorId,
                Long clinicRoomId){
            this.date = date;
            this.time = time;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.clinicRoomId = clinicRoomId;
            this.validateSelf();
        }
    }
}
