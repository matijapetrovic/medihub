package org.medihub.application.services.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentDateCount;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryOutput;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.finished_appointment.GetFinishedAppointmentsPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.reviewing.LoadClinicReviewPort;
import org.medihub.application.ports.outgoing.reviewing.LoadDoctorReviewPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.domain.patient.Patient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAppointmentHistoryService implements GetAppointmentHistoryQuery {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadPatientPort loadPatientPort;
    private final GetFinishedAppointmentsPort getFinishedAppointmentsPort;
    private final LoadClinicReviewPort loadClinicReviewPort;
    private final LoadDoctorReviewPort loadDoctorReviewPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<GetAppointmentHistoryOutput> getAppointmentHistory() {
        Account account = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(account.getId());

        List<FinishedAppointment> finishedAppointments =
                getFinishedAppointmentsPort.getFinishedAppointments(patient.getId());

        return mapToOutput(finishedAppointments);
    }

    @Override
    public List<GetAppointmentDateCount> getClinicAppointmentHistory(FinishedAppointmentQuery finishedAppointmentQuery) {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());

        List<FinishedAppointment> finishedAppointments =
                getFinishedAppointmentsPort.getAllFinishedAppointmentsForCurrentClinic(clinicAdmin.getClinic().getId());
        return countAppearance(
                filterByType(finishedAppointments, finishedAppointmentQuery),
                finishedAppointmentQuery.getType());
    }

    private List<GetAppointmentDateCount> countAppearance(List<FinishedAppointment> finishedAppointments, String type) {
        if(type.equals("day")) {
            return  countAppearanceByTimes(finishedAppointments);
        } else {
            return countAppearanceByDate(finishedAppointments);
        }
    }

    private List<GetAppointmentDateCount> countAppearanceByDate(List<FinishedAppointment> finishedAppointments) {
        HashMap<LocalDate, GetAppointmentDateCount> countDict = new HashMap<>();
        for(FinishedAppointment item: finishedAppointments) {
            LocalDate date = item.getAppointment().getDate();
            if (!countDict.containsKey(date)){
                countDict.put(date, new GetAppointmentDateCount(date.toString(), "" , 1));
            } else {
                countDict.put(date, new GetAppointmentDateCount(date.toString(), "", countDict.get(date).getCount() + 1));
            }
        }
        return new ArrayList<GetAppointmentDateCount>(countDict.values());
    }

    private List<GetAppointmentDateCount> countAppearanceByTimes(List<FinishedAppointment> finishedAppointments) {
        HashMap<LocalTime, GetAppointmentDateCount> countDict = new HashMap<>();
        for(FinishedAppointment item: finishedAppointments) {
            LocalTime time = item.getAppointment().getTime();
            if (!countDict.containsKey(time)){
                countDict.put(time, new GetAppointmentDateCount("", time.toString() , 1));
            } else {
                countDict.put(time, new GetAppointmentDateCount("", time.toString(), countDict.get(time).getCount() + 1));
            }
        }
        return new ArrayList<GetAppointmentDateCount>(countDict.values());
    }

    private List<FinishedAppointment> filterByType(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery)
    {
        if(finishedAppointmentQuery.getType().equals("year")) {
            return filterByYear(finishedAppointments, finishedAppointmentQuery);
        } else if(finishedAppointmentQuery.getType().equals("month")) {
            return filterByMonth(finishedAppointments, finishedAppointmentQuery);
        }else {
            return filterByDay(finishedAppointments, finishedAppointmentQuery);
        }
    }

    private List<FinishedAppointment> filterByYear(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery
    ) {
        return finishedAppointments
                .stream()
                .filter(item -> item.getAppointment().getDate().getYear() == finishedAppointmentQuery.getDate().getYear())
                .collect(Collectors.toList());
    }

    private List<FinishedAppointment> filterByMonth(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery
    ) {
        return finishedAppointments
                .stream()
                .filter(item ->
                        item.getAppointment().getDate().getYear() == finishedAppointmentQuery.getDate().getYear() &&
                                item.getAppointment().getDate().getMonth() == finishedAppointmentQuery.getDate().getMonth())
                .collect(Collectors.toList());
    }

    private List<FinishedAppointment> filterByDay(
            List<FinishedAppointment> finishedAppointments,
            FinishedAppointmentQuery finishedAppointmentQuery
    ) {
        return finishedAppointments
                .stream()
                .filter(item ->
                        item.getAppointment().getDate().equals(finishedAppointmentQuery.getDate())).collect(Collectors.toList());
    }

    private List<GetAppointmentHistoryOutput> mapToOutput(List<FinishedAppointment> appointments) {
        return appointments
                .stream()
                .map(appointment ->
                        new GetAppointmentHistoryOutput(
                            appointment.getId(),
                            appointment.getAppointment().getDoctor().getSpecialization().getName(), // mozda predugacko
                            appointment.getAppointment().getDoctor().getFullName(),
                            appointment.getAppointment().getDoctor().getClinic().getName(),
                            appointment.getAppointment().getDate().toString(),
                            appointment.getAppointment().getTime().toString(),
                            getClinicRating(appointment),
                            getDoctorRating(appointment)
                        ))
                .collect(Collectors.toList());
    }

    private BigDecimal getClinicRating(FinishedAppointment appointment) {
        ClinicReview review = loadClinicReviewPort.loadByAppointmentId(appointment.getId());
        return review != null ? review.getRating() : null;
    }

    private BigDecimal getDoctorRating(FinishedAppointment appointment) {
        MedicalDoctorReview review = loadDoctorReviewPort.loadByAppointmentId(appointment.getId());
        return review != null ? review.getRating() : null;
    }
}
