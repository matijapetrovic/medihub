package org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.springframework.stereotype.Component;
import java.util.Optional;
import org.medihub.application.exceptions.NotFoundException;

@Component
@RequiredArgsConstructor
public class MedicalDoctorAppointmentScheduleItemAdapter
        implements LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort {
    private final MedicalDoctorAppointmentScheduleItemRepository repository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public MedicalDoctorAppointmentScheduleItem loadById(Long id) throws NotFoundException {
        Optional<MedicalDoctorAppointmentScheduleJpaItem> scheduleJpaItem =
                repository.findById(id);
        if (scheduleJpaItem.isEmpty()) {
            throw new NotFoundException("Schedule item not found");
        }
        MedicalDoctorAppointmentScheduleJpaItem jpaItem = scheduleJpaItem.get();
        return new MedicalDoctorAppointmentScheduleItem(
                jpaItem.getId(),
                jpaItem.getStartTime().toLocalDateTime().toLocalTime(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(jpaItem.getType()).isPresent()? 
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(jpaItem.getType())?
                        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(jpaItem.getType()).get() : null,
                appointmentMapper.mapToDomainEntity(jpaItem.getAppointment())
        );
    }
}
