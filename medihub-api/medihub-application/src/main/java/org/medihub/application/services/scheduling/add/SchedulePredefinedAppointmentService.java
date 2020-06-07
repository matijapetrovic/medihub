package org.medihub.application.services.scheduling.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.scheduling.SchedulePredefinedAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.predefined_appointment.DeletePredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.predefined_appointment.LoadPredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.scheduling.LoadClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.LoadDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.SaveClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.SaveDoctorDailySchedulePort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.patient.Patient;
import org.medihub.domain.scheduling.DailySchedule;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@RequiredArgsConstructor
public class SchedulePredefinedAppointmentService implements SchedulePredefinedAppointmentUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final LoadPredefinedAppointmentPort loadPredefinedAppointmentPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final LoadDoctorDailySchedulePort loadDoctorDailySchedulePort;
    private final SaveDoctorDailySchedulePort saveDoctorDailySchedulePort;
    private final LoadClinicRoomDailySchedulePort loadClinicRoomDailySchedulePort;
    private final SaveClinicRoomDailySchedulePort saveClinicRoomDailySchedulePort;
    private final DeletePredefinedAppointmentPort deletePredefinedAppointmentPort;

    @Override
    public void schedulePredefinedAppointment(@NotNull Long appointmentId) {
        Patient patient = getAuthenticatedPatient();
        PredefinedAppointment predefinedAppointment = loadPredefinedAppointment(appointmentId);
        Appointment appointment = scheduleAppointment(predefinedAppointment, patient);

        addAppointmentToDoctorSchedule(appointment);
        addAppointmentToRoomSchedule(appointment);
        deletePredefinedAppointment(predefinedAppointment);
    }

    private Patient getAuthenticatedPatient() {
        Account account = getAuthenticatedPort.getAuthenticated();
        return loadPatientPort.loadPatientByAccountId(account.getId());
    }

    private PredefinedAppointment loadPredefinedAppointment(Long appointmentId) {
        return loadPredefinedAppointmentPort.loadPredefinedAppointment(appointmentId);
    }

    private Appointment scheduleAppointment(PredefinedAppointment predefinedAppointment, Patient patient) {
        return saveAppointmentPort.saveAppointment(predefinedAppointment.schedule(patient));
    }

    private void addAppointmentToDoctorSchedule(Appointment appointment) {
        DailySchedule<MedicalDoctorScheduleItem> doctorSchedule = loadDoctorDailySchedule(appointment);
        ensureDoctorIsAvailable(doctorSchedule, appointment.getTime());

        MedicalDoctorScheduleItem scheduleItem = createDoctorScheduleItem(appointment);
        doctorSchedule.addToSchedule(scheduleItem);

        saveDoctorDailySchedule(appointment, doctorSchedule);
    }

    private DailySchedule<MedicalDoctorScheduleItem> loadDoctorDailySchedule(Appointment appointment) {
        return loadDoctorDailySchedulePort.loadDailySchedule(
                appointment.getDoctor().getId(),
                appointment.getDate());
    }

    private void ensureDoctorIsAvailable(DailySchedule<MedicalDoctorScheduleItem> doctorSchedule, LocalTime time) {
        if (!doctorSchedule.isAvailable(time)) {
            // throw exception
        }
    }

    private MedicalDoctorScheduleItem createDoctorScheduleItem(Appointment appointment) {
        return new MedicalDoctorAppointmentScheduleItem(
                null,
                appointment.getTime(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.APPOINTMENT,
                appointment);
    }

    private void saveDoctorDailySchedule(Appointment appointment,
                                         DailySchedule<MedicalDoctorScheduleItem> dailySchedule) {
        saveDoctorDailySchedulePort.saveDoctorDailySchedule(
                appointment.getDoctor(),
                appointment.getDate(),
                dailySchedule);
    }

    private void addAppointmentToRoomSchedule(Appointment appointment) {
        DailySchedule<ClinicRoomScheduleItem> clinicSchedule = loadClinicRoomDailySchedule(appointment);
        ensureClinicRoomIsAvailable(clinicSchedule, appointment.getTime());

        ClinicRoomScheduleItem scheduleItem = createClinicRoomScheduleItem(appointment);
        clinicSchedule.addToSchedule(scheduleItem);

        saveClinicRoomDailySchedule(appointment, clinicSchedule);
    }

    private DailySchedule<ClinicRoomScheduleItem> loadClinicRoomDailySchedule(Appointment appointment) {
        return loadClinicRoomDailySchedulePort.loadClinicRoomDailySchedule(
                appointment.getClinicRoom().getId(),
                appointment.getDate());
    }

    private void ensureClinicRoomIsAvailable(DailySchedule<ClinicRoomScheduleItem> clinicSchedule, LocalTime time) {
        if (!clinicSchedule.isAvailable(time)) {
            // throw exception
        }
    }

    private ClinicRoomScheduleItem createClinicRoomScheduleItem(Appointment appointment) {
        return new ClinicRoomScheduleItem(
                null,
                appointment.getTime());
    }

    private void saveClinicRoomDailySchedule(Appointment appointment, DailySchedule<ClinicRoomScheduleItem> dailySchedule) {
        saveClinicRoomDailySchedulePort.saveClinicRoomDailySchedule(
                appointment.getClinicRoom(),
                appointment.getDate(),
                dailySchedule);
    }

    private void deletePredefinedAppointment(PredefinedAppointment predefinedAppointment) {
        deletePredefinedAppointmentPort.deletePredefinedAppointment(predefinedAppointment.getId());
    }
}
