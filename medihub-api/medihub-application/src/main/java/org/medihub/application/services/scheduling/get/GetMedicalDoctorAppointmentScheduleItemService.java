package org.medihub.application.services.scheduling.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.scheduling.GetAppointmentScheduleItemByAppointmentIdUseCase;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
public class GetMedicalDoctorAppointmentScheduleItemService
        implements GetAppointmentScheduleItemByAppointmentIdUseCase {
    private final LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort port;

    @Override
    public MedicalDoctorAppointmentScheduleItem getItem(Long id) {
        return port.loadById(id);
    }
}
