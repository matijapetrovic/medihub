package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorSchedulePort;
import org.medihub.application.ports.outgoing.leave_request.ApproveLeaveRequestPort;
import org.medihub.application.ports.outgoing.scheduling.LoadDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.SaveDoctorDailySchedulePort;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.medical_doctor.*;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorRepository;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleJpaItem;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item.MedicalDoctorVacationScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item.MedicalDoctorVacationScheduleJpaItem;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class MedicalDoctorScheduleAdapter implements
        LoadDoctorDailySchedulePort,
        SaveDoctorDailySchedulePort,
        GetDoctorSchedulePort,
        AddAppointmentToMedicalDoctorSchedulePort,
        ApproveLeaveRequestPort,
        DeleteAppointmentScheduleItemPort {
    private final AppointmentMapper appointmentMapper;
    private final MedicalDoctorMapper doctorMapper;
    private final MedicalDoctorScheduleMapper medicalDoctorScheduleMapper;

    private final MedicalDoctorRepository medicalDoctorRepository;
    private final MedicalDoctorScheduleItemRepository scheduleItemRepository;
    private final MedicalDoctorAppointmentScheduleItemRepository appointmentScheduleItemRepository;
    private final MedicalDoctorVacationScheduleItemRepository vacationScheduleItemRepository;

    @Override
    public DailySchedule<MedicalDoctorScheduleItem> loadDailySchedule(Long doctorId, LocalDate date) {
        MedicalDoctorJpaEntity doctor =
                medicalDoctorRepository.findById(doctorId)
                        .orElseThrow(EntityNotFoundException::new);

        Timestamp start = Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT));
        Timestamp end = Timestamp.valueOf(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT));

        Set<MedicalDoctorScheduleItemJpaEntity> scheduleJpaItems =
                scheduleItemRepository.findAllByDoctorIdAndStartTimeBetween(doctorId, start, end);

        return medicalDoctorScheduleMapper.mapToScheduleDomainEntity(scheduleJpaItems);
    }

    @Override
    public MedicalDoctorSchedule getDoctorSchedule(Long doctorId) {
        Set<MedicalDoctorScheduleItemJpaEntity> scheduleItems = scheduleItemRepository
                .findAllByDoctorId(doctorId);

        Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> dailySchedules =
                new HashMap<>();
        for (MedicalDoctorScheduleItemJpaEntity scheduleItem : scheduleItems) {
            LocalDate date = scheduleItem.getStartTime().toLocalDateTime().toLocalDate();
            DailySchedule<MedicalDoctorScheduleItem> dailySchedule = dailySchedules.get(date);
            if (dailySchedule == null) {
                dailySchedule = new DailySchedule<>(null);
                dailySchedules.put(date, dailySchedule);
            }
            dailySchedule.addToSchedule(medicalDoctorScheduleMapper.mapToScheduleItemDomainEntity(scheduleItem));

        }

        return new MedicalDoctorSchedule(dailySchedules);
    }

    @Override
    public void addAppointmentToSchedule(
            LocalDate date,
            LocalTime time,
            Appointment appointment) {
        MedicalDoctorAppointmentScheduleJpaItem scheduleJpaItem =
                new MedicalDoctorAppointmentScheduleJpaItem(
                        null,
                        doctorMapper.mapToJpaEntity(appointment.getDoctor()),
                        Timestamp.valueOf(LocalDateTime.of(date, time)),
                        MedicalDoctorScheduleItemType.APPOINTMENT.getOrdinal(),
                        appointmentMapper.mapToJpaEntity(appointment));

        scheduleItemRepository.save(scheduleJpaItem);
    }

    @Override
    public void approveLeaveRequest(LeaveRequest leaveRequest) {
        MedicalDoctorVacationScheduleJpaItem vacationScheduleJpaItem =
            new MedicalDoctorVacationScheduleJpaItem(
                    null,
                    doctorMapper.mapToJpaEntity(leaveRequest.getMedicalDoctor()),
                    Timestamp.valueOf(LocalDateTime.of(leaveRequest.getStart(), LocalTime.NOON)),
                    MedicalDoctorScheduleItemType.valueOf(leaveRequest.getType()).getOrdinal(),
                    Timestamp.valueOf(LocalDateTime.of(leaveRequest.getEnd(), LocalTime.MIDNIGHT)));
        vacationScheduleItemRepository.save(vacationScheduleJpaItem);
    }

    @Override
    public void deleteAppointmentItem(Long id) {
        appointmentScheduleItemRepository.deleteById(id);
    }

    @Override
    public void saveDoctorDailySchedule(MedicalDoctor doctor,
                                        LocalDate date,
                                        DailySchedule<MedicalDoctorScheduleItem> dailySchedule) {
        Set<MedicalDoctorScheduleItemJpaEntity> doctorDailySchedule =
                medicalDoctorScheduleMapper.mapToScheduleJpaEntity(
                        dailySchedule,
                        doctor,
                        date);
        scheduleItemRepository.saveAll(doctorDailySchedule);
    }
}
