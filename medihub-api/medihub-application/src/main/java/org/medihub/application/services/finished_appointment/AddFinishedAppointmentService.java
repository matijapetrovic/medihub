package org.medihub.application.services.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.AddFinishedAppointmentUseCase;
import org.medihub.application.ports.incoming.finished_appointment.FinishedAppointmentOutput;
import org.medihub.application.ports.outgoing.appointment.GetAppointmentPort;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;
import org.medihub.application.ports.outgoing.diagnosis.GetDiagnosisByIdPort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemPort;
import org.medihub.application.ports.outgoing.drugs.GetDrugByIdPort;
import org.medihub.application.ports.outgoing.finished_appointment.SaveFinishedAppointmentPort;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.Drug;
import org.medihub.domain.Prescription;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.FinishedAppointment;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AddFinishedAppointmentService implements AddFinishedAppointmentUseCase {
    private final GetAppointmentPort getAppointmentPort;
    private final GetDiagnosisByIdPort getDiagnosisByIdPort;
    private final SaveFinishedAppointmentPort saveFinishedAppointmentPort;
    private final GetDrugByIdPort getDrugByIdPort;
    private final SavePrescriptionPort savePrescriptionPort;
    private final DeleteAppointmentScheduleItemPort deleteAppointmentScheduleItemPort;

    @Override
    public FinishedAppointmentOutput addFinishedAppointment(AddFinishedAppointmentCommand command) {
        Appointment appointment = getAppointmentPort.getAppointmentById(command.getAppointment());
        Diagnosis diagnosis = getDiagnosisByIdPort.getDiagnosisById(command.getDiagnosis());

        FinishedAppointment finishedAppointment = new FinishedAppointment(null,
                command.getDescription(),
                appointment,
                diagnosis);

        FinishedAppointment retVal = saveFinishedAppointmentPort.saveFinishedAppointment(finishedAppointment);
        deleteAppointmentScheduleItemPort.deleteAppointmentItem(command.getItemId());

        for(Long id : command.getDrugs()) {
            Drug d = getDrugByIdPort.getDrugById(id);
            Prescription prescription = new Prescription(null, d, retVal, null);
            savePrescriptionPort.savePrescription(prescription);
        }

        return createOutput(retVal);
    }

    private FinishedAppointmentOutput createOutput(FinishedAppointment finishedAppointment) {
        return new FinishedAppointmentOutput(
                finishedAppointment.getId(),
                finishedAppointment.getDescription(),
                finishedAppointment.getAppointment().getId(),
                finishedAppointment.getDiagnosis().getId()
        );
    }
}
