package org.medihub.application.services.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.finished_appointment.AddFinishedAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.LoadAppointmentPort;
import org.medihub.application.ports.incoming.finished_appointment.GetFinishedAppointmentOutput;
import org.medihub.application.ports.outgoing.diagnosis.GetDiagnosisByIdPort;
import org.medihub.application.ports.outgoing.doctor.DeleteAllAppointmentScheduleItemByAppointmentIdPort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemByDoctorIdPort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemPort;
import org.medihub.application.ports.outgoing.drugs.GetDrugByIdPort;
import org.medihub.application.ports.outgoing.finished_appointment.SaveFinishedAppointmentPort;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.application.ports.outgoing.reviewing.LoadClinicReviewPort;
import org.medihub.application.ports.outgoing.reviewing.LoadDoctorReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveClinicReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveDoctorReviewPort;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.Drug;
import org.medihub.domain.Prescription;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.appointment.Operation;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.domain.patient.Patient;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class AddFinishedAppointmentService implements AddFinishedAppointmentUseCase {
    private final LoadAppointmentPort loadAppointmentPort;
    private final GetDiagnosisByIdPort getDiagnosisByIdPort;
    private final SaveFinishedAppointmentPort saveFinishedAppointmentPort;
    private final GetDrugByIdPort getDrugByIdPort;
    private final SavePrescriptionPort savePrescriptionPort;
    private final LoadClinicReviewPort loadClinicReviewPort;
    private final LoadDoctorReviewPort loadDoctorReviewPort;
    private final SaveClinicReviewPort saveClinicReviewPort;
    private final SaveDoctorReviewPort saveDoctorReviewPort;
    private final DeleteAllAppointmentScheduleItemByAppointmentIdPort deleteAllAppointmentScheduleItemByAppointmentIdPort;

    @Override
    @Transactional
    public GetFinishedAppointmentOutput addFinishedAppointment(AddFinishedAppointmentCommand command) throws NotFoundException {
        Appointment appointment = loadAppointmentPort.getAppointmentById(command.getAppointment());
        Diagnosis diagnosis = getDiagnosisByIdPort.getDiagnosisById(command.getDiagnosis());

        FinishedAppointment finishedAppointment = new FinishedAppointment(null,
                command.getDescription(),
                appointment,
                diagnosis);

        FinishedAppointment retVal = saveFinishedAppointmentPort.saveFinishedAppointment(finishedAppointment);
        deleteAllAppointmentScheduleItemByAppointmentIdPort.deleteAll(retVal.getAppointment().getId());

        for  (Long id : command.getDrugs()) {
            Drug drug = getDrugByIdPort.getDrugById(id);
            Prescription prescription = new Prescription(null, drug, retVal, null);
            savePrescriptionPort.savePrescription(prescription);
        }

        updateClinicReview(appointment.getDoctor().getClinic(), appointment.getPatient());
        updateDoctorReview(appointment.getDoctor(), appointment.getPatient());

        return createOutput(retVal);
    }

    private void updateClinicReview(Clinic clinic, Patient patient) {
        ClinicReview clinicReview = loadClinicReviewPort.loadByPatientIdAndClinicId(
                patient.getId(),
                clinic.getId());

        if (clinicReview == null)
            clinicReview = new ClinicReview(null, null, patient ,clinic, true);

        clinicReview.enableReview();
        saveClinicReviewPort.saveClinicReview(clinicReview);
    }

    private void updateDoctorReview(MedicalDoctor doctor, Patient patient) {
        MedicalDoctorReview doctorReview = loadDoctorReviewPort.loadByPatientIdAndDoctorId(
                patient.getId(),
                doctor.getId());

        if (doctorReview == null)
            doctorReview = new MedicalDoctorReview(null, null, patient, doctor, true);

        doctorReview.enableReview();
        saveDoctorReviewPort.saveDoctorReview(doctorReview);
    }

    private GetFinishedAppointmentOutput createOutput(FinishedAppointment finishedAppointment) {
        return new GetFinishedAppointmentOutput(
                finishedAppointment.getId(),
                finishedAppointment.getDescription(),
                finishedAppointment.getAppointment().getId(),
                finishedAppointment.getDiagnosis().getId(),
                finishedAppointment.getAppointment().getDate().toString(),
                finishedAppointment.getAppointment().getTime().toString(),
                finishedAppointment.getDiagnosis().getName(),
                getType(finishedAppointment)
        );
    }

    private String getType(FinishedAppointment finishedAppointment) {
        return (finishedAppointment.getAppointment() instanceof Operation)? "OPERATION":"APPOINTMENT";
    }
}
