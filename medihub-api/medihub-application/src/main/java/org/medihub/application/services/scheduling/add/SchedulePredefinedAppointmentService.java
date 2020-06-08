package org.medihub.application.services.scheduling.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotAvailableException;
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
    private final DeletePredefinedAppointmentPort deletePredefinedAppointmentPort;

    @Override
    public void schedulePredefinedAppointment(@NotNull Long appointmentId) {
        Patient patient = getAuthenticatedPatient();
        PredefinedAppointment predefinedAppointment = loadPredefinedAppointment(appointmentId);
        Appointment appointment = scheduleAppointment(predefinedAppointment, patient);

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

    private void deletePredefinedAppointment(PredefinedAppointment predefinedAppointment) {
        deletePredefinedAppointmentPort.deletePredefinedAppointment(predefinedAppointment.getId());
    }
}
