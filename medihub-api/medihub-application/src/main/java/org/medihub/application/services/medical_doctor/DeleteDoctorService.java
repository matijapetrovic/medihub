package org.medihub.application.services.medical_doctor;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.medical_doctor.DeleteDoctorUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.account.DeleteAccountPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorScheduleItemPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class DeleteDoctorService implements DeleteDoctorUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final LoadDoctorPort loadDoctorPort;
    private final LoadMedicalDoctorScheduleItemPort loadMedicalDoctorScheduleItemPort;
    private final SaveDoctorPort saveDoctorPort;
    private final DeleteAccountPort deleteAccountPort;

    @Override
    public void deleteDoctor(@NotNull Long doctorId) throws ForbiddenException, NotFoundException {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());

        MedicalDoctor doctor = loadDoctorPort.loadDoctor(doctorId);
        if (doctor.getArchived())
            throw new NotFoundException("Doctor not found");
        ensureClinicAdminCanAccess(doctor, clinicAdmin);
        ensureDoctorCanBeDeleted(doctor);

        Account doctorAccount = doctor.getPersonalInfo().getAccount();
        doctor.archive();
        saveDoctorPort.saveDoctor(doctor);
        deleteAccountPort.deleteAccountById(doctorAccount.getId());
    }

    private void ensureClinicAdminCanAccess(MedicalDoctor doctor, ClinicAdmin admin) throws ForbiddenException {
        if (!admin.getClinic().getId().equals(doctor.getClinic().getId()))
            throw new ForbiddenException("Clinic admin cannot access doctor");
    }

    private void ensureDoctorCanBeDeleted(MedicalDoctor doctor) throws ForbiddenException {
        if (loadMedicalDoctorScheduleItemPort.countFutureScheduleItems(doctor.getId()) > 0)
            throw new ForbiddenException("Doctor cannot be deleted");
    }
}
