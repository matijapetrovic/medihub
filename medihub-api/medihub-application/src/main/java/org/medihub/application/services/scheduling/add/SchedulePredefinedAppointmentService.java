package org.medihub.application.services.scheduling.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.scheduling.SchedulePredefinedAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.predefined_appointment.DeletePredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.predefined_appointment.LoadPredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.DeleteMedicalDoctorScheduleItemPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorScheduleItemPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalDoctorScheduleItemPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.patient.Patient;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SchedulePredefinedAppointmentService implements SchedulePredefinedAppointmentUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final LoadPredefinedAppointmentPort loadPredefinedAppointmentPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final LoadMedicalDoctorScheduleItemPort loadMedicalDoctorScheduleItemPort;
    private final DeleteMedicalDoctorScheduleItemPort deleteMedicalDoctorScheduleItemPort;
    private final SaveMedicalDoctorScheduleItemPort saveMedicalDoctorScheduleItemPort;
    private final DeletePredefinedAppointmentPort deletePredefinedAppointmentPort;
    private final SendEmailPort sendEmailPort;

    @Override
    public void schedulePredefinedAppointment(@NotNull Long appointmentId) {
        Patient patient = getAuthenticatedPatient();
        PredefinedAppointment predefinedAppointment = loadPredefinedAppointment(appointmentId);
        Appointment appointment = scheduleAppointment(predefinedAppointment, patient);
        updateDoctorSchedule(predefinedAppointment, appointment);
        deletePredefinedAppointment(predefinedAppointment);
        notifyPatient(appointment);
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

    private void updateDoctorSchedule(PredefinedAppointment predefinedAppointment, Appointment appointment) {
        MedicalDoctorScheduleItem scheduleItem =
                loadMedicalDoctorScheduleItemPort.loadPredefinedAppointmentScheduleItemByPredefinedAppointmentId(
                        predefinedAppointment.getId());
        deleteMedicalDoctorScheduleItemPort.deleteMedicalDoctorScheduleItem(scheduleItem.getId());
        scheduleItem = createMedicalDoctorScheduleItem(appointment);
        saveMedicalDoctorScheduleItemPort.saveMedicalDoctorScheduleItem(
                scheduleItem,
                appointment.getDoctor(),
                appointment.getDate());
    }

    private MedicalDoctorScheduleItem createMedicalDoctorScheduleItem(Appointment appointment) {
        return new MedicalDoctorAppointmentScheduleItem(
                null,
                appointment.getTime(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.APPOINTMENT,
                appointment);
    }

    private void deletePredefinedAppointment(PredefinedAppointment predefinedAppointment) {
        deletePredefinedAppointmentPort.deletePredefinedAppointment(predefinedAppointment.getId());
    }

    private void notifyPatient(Appointment appointment) {
        String to = "medihub.mail@gmail.com";
        String subject = "Appointment scheduling confirmation";
        String text = String.format("You have successfully scheduled an appointment at %s with %s at %s",
                appointment.getDoctor().getClinic().getName(),
                appointment.getDoctor().getFullName(),
                LocalDateTime.of(appointment.getDate(), appointment.getTime()));
        sendEmailPort.sendEmail(to, subject, text);
    }
}
