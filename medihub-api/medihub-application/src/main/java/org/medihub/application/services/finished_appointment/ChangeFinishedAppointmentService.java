package org.medihub.application.services.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.ChangeFinishedAppointmentUseCase;
import org.medihub.application.ports.outgoing.diagnosis.GetDiagnosisByIdPort;
import org.medihub.application.ports.outgoing.finished_appointment.GetFinishedAppointmentPort;
import org.medihub.application.ports.outgoing.finished_appointment.SaveFinishedAppointmentPort;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.appointment.FinishedAppointment;

@RequiredArgsConstructor
public class ChangeFinishedAppointmentService implements ChangeFinishedAppointmentUseCase {
    private final GetFinishedAppointmentPort getFinishedAppointmentPort;
    private final GetDiagnosisByIdPort getDiagnosisByIdPort;
    private final SaveFinishedAppointmentPort saveFinishedAppointmentPort;

    @Override
    public void changeFinishedAppointment(ChangeFinishedAppointmentCommand command) {
        FinishedAppointment finishedAppointment = getFinishedAppointmentPort.getFinishedAppointment(command.getId());
        Diagnosis diagnosis = getDiagnosisByIdPort.getDiagnosisById(command.getDiagnosis());

        finishedAppointment.setDiagnosis(diagnosis);
        finishedAppointment.setDescription(command.getDescription());

        saveFinishedAppointmentPort.saveFinishedAppointment(finishedAppointment);
    }
}
