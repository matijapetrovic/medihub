package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorSchedulePort;
import org.medihub.application.ports.outgoing.leave_request.ApproveLeaveRequestPort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.SaveDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.DeleteMedicalDoctorScheduleItemPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorScheduleItemPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalDoctorScheduleItemPort;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.medical_doctor.*;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.appointment.AbstractAppointmentMapper;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorRepository;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleJpaItem;
import org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item.MedicalDoctorPredefinedAppointmentScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item.MedicalDoctorPredefinedAppointmentScheduleJpaItem;
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
        DeleteAppointmentScheduleItemPort,
        SaveMedicalDoctorScheduleItemPort,
        LoadMedicalDoctorScheduleItemPort,
        DeleteMedicalDoctorScheduleItemPort {
    private final AbstractAppointmentMapper abstractAppointmentMapper;
    private final MedicalDoctorMapper doctorMapper;
    private final MedicalDoctorScheduleMapper medicalDoctorScheduleMapper;

    private final MedicalDoctorRepository medicalDoctorRepository;
    private final MedicalDoctorScheduleItemRepository scheduleItemRepository;
    private final MedicalDoctorAppointmentScheduleItemRepository appointmentScheduleItemRepository;
    private final MedicalDoctorVacationScheduleItemRepository vacationScheduleItemRepository;
    private final MedicalDoctorPredefinedAppointmentScheduleItemRepository predefinedAppointmentScheduleItemRepository;

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
                        (AppointmentJpaEntity) abstractAppointmentMapper.mapToJpaEntity(appointment));

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

    @Override
    public void deleteMedicalDoctorScheduleItem(Long scheduleItemId) {
        scheduleItemRepository.deleteById(scheduleItemId);
    }

    @Override
    public MedicalDoctorPredefinedAppointmentScheduleItem loadPredefinedAppointmentScheduleItemByPredefinedAppointmentId(
            Long predefinedAppointmentId) {
        MedicalDoctorPredefinedAppointmentScheduleJpaItem scheduleItem =
                predefinedAppointmentScheduleItemRepository.findByPredefinedAppointmentId(predefinedAppointmentId)
                .orElseThrow(EntityNotFoundException::new);
        return (MedicalDoctorPredefinedAppointmentScheduleItem) medicalDoctorScheduleMapper
                .mapToScheduleItemDomainEntity(scheduleItem);
    }

    @Override
    public void saveMedicalDoctorScheduleItem(
            MedicalDoctorScheduleItem scheduleItem,
            MedicalDoctor doctor,
            LocalDate date) {
        scheduleItemRepository.save(
                medicalDoctorScheduleMapper.mapToScheduleItemJpaEntity(
                    scheduleItem,
                    doctor,
                    date));
    }
}
