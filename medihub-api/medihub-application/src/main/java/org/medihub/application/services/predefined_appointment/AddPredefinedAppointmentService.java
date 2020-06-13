package org.medihub.application.services.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment_type.GetAppointmentTypesPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.predefined_appointment.AddPredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.SaveClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.SaveDoctorDailySchedulePort;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorPredefinedAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.io.NotActiveException;
import java.time.LocalTime;

@RequiredArgsConstructor
public class AddPredefinedAppointmentService implements AddPredefinedAppointmentUseCase {
    private final AddPredefinedAppointmentPort addPredefinedAppointmentPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final GetAppointmentTypesPort getAppointmentTypesPort;
    private final GetDoctorsPort getDoctorsPort;
    private final LoadDoctorDailySchedulePort loadDoctorDailySchedulePort;
    private final SaveDoctorDailySchedulePort saveDoctorDailySchedulePort;
    private final LoadClinicRoomDailySchedulePort loadClinicRoomDailySchedulePort;
    private final SaveClinicRoomDailySchedulePort saveClinicRoomDailySchedulePort;

    @Override
    public void addPredefinedAppointment(AddPredefinedAppointmentCommand command) throws NotAvailableException, NotFoundException {
        PredefinedAppointment predefinedAppointment =
                new PredefinedAppointment(
                        null,
                        getDoctorsPort.getMedicalDoctorById(command.getDoctorId()),
                        command.getDate(),
                        LocalTime.parse(command.getStart()),
                        command.getDuration(),
                        getClinicRoomsPort.getClinicRoom(command.getClinicRoomId()),
                        getAppointmentTypesPort.getById(command.getAppointmentTypeId()),
                        command.getPrice(),
                        command.getDiscount()
                );
        predefinedAppointment = addPredefinedAppointmentPort.addPredefinedAppointment(predefinedAppointment);
        addPredefinedAppointmentToDoctorSchedule(predefinedAppointment);
        addPredefinedAppointmentToRoomSchedule(predefinedAppointment);
    }

    private void addPredefinedAppointmentToDoctorSchedule(PredefinedAppointment predefinedAppointment) throws NotAvailableException {
        DailySchedule<MedicalDoctorScheduleItem> doctorSchedule = loadDoctorDailySchedule(predefinedAppointment);
        ensureDoctorIsAvailable(doctorSchedule, predefinedAppointment.getStart());

        MedicalDoctorScheduleItem scheduleItem = createDoctorScheduleItem(predefinedAppointment);
        doctorSchedule.addToSchedule(scheduleItem);

        saveDoctorDailySchedule(predefinedAppointment, doctorSchedule);
    }

    private DailySchedule<MedicalDoctorScheduleItem> loadDoctorDailySchedule(PredefinedAppointment predefinedAppointment) {
        return loadDoctorDailySchedulePort.loadDailySchedule(
                predefinedAppointment.getDoctor().getId(),
                predefinedAppointment.getDate());
    }

    private void ensureDoctorIsAvailable(DailySchedule<MedicalDoctorScheduleItem> doctorSchedule, LocalTime time) throws NotAvailableException {
        if (!doctorSchedule.isAvailable(time)) {
            throw new NotAvailableException("Doctor is not available for required time!");
        }
    }

    private MedicalDoctorScheduleItem createDoctorScheduleItem(PredefinedAppointment predefinedAppointment) {
        return new MedicalDoctorPredefinedAppointmentScheduleItem(
                null,
                predefinedAppointment.getStart(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.PREDEFINED_APPOINTMENT,
                predefinedAppointment);
    }

    private void saveDoctorDailySchedule(PredefinedAppointment predefinedAppointment,
                                         DailySchedule<MedicalDoctorScheduleItem> dailySchedule) {
        saveDoctorDailySchedulePort.saveDoctorDailySchedule(
                predefinedAppointment.getDoctor(),
                predefinedAppointment.getDate(),
                dailySchedule);
    }

    private void addPredefinedAppointmentToRoomSchedule(PredefinedAppointment predefinedAppointment) throws NotAvailableException {
        DailySchedule<ClinicRoomScheduleItem> clinicSchedule = loadClinicRoomDailySchedule(predefinedAppointment);
        ensureClinicRoomIsAvailable(clinicSchedule, predefinedAppointment.getStart());

        ClinicRoomScheduleItem scheduleItem = createClinicRoomScheduleItem(predefinedAppointment);
        clinicSchedule.addToSchedule(scheduleItem);

        saveClinicRoomDailySchedule(predefinedAppointment, clinicSchedule);
    }

    private DailySchedule<ClinicRoomScheduleItem> loadClinicRoomDailySchedule(PredefinedAppointment predefinedAppointment) {
        return loadClinicRoomDailySchedulePort.loadClinicRoomDailySchedule(
                predefinedAppointment.getClinicRoom().getId(),
                predefinedAppointment.getDate());
    }

    private void ensureClinicRoomIsAvailable(DailySchedule<ClinicRoomScheduleItem> clinicSchedule, LocalTime time) throws NotAvailableException {
        if (!clinicSchedule.isAvailable(time)) {
            throw new NotAvailableException("Clinic room is not available for required time");
        }
    }

    private ClinicRoomScheduleItem createClinicRoomScheduleItem(PredefinedAppointment predefinedAppointment) {
        return new ClinicRoomScheduleItem(
                null,
                predefinedAppointment.getStart());
    }

    private void saveClinicRoomDailySchedule(PredefinedAppointment predefinedAppointment,
                                             DailySchedule<ClinicRoomScheduleItem> dailySchedule) {
        saveClinicRoomDailySchedulePort.saveClinicRoomDailySchedule(
                predefinedAppointment.getClinicRoom(),
                predefinedAppointment.getDate(),
                dailySchedule);
    }

}
