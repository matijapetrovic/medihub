package org.medihub.application.services.scheduling.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.scheduling.GetAppointmentScheduleItemByAppointmentIdUseCase;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;

@RequiredArgsConstructor
public class GetMedicalDoctorAppointmentScheduleItemService
        implements GetAppointmentScheduleItemByAppointmentIdUseCase {
    private final LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort port;

    @Override
    public MedicalDoctorAppointmentScheduleItem getItem(Long id) throws NotFoundException {
        return port.loadById(id);
    }
}
