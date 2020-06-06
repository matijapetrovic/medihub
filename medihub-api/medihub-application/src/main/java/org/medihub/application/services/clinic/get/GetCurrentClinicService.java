package org.medihub.application.services.clinic.get;

import lombok.AllArgsConstructor;
import org.medihub.application.ports.incoming.clinic.GetCurrentClinicResponse;
import org.medihub.application.ports.incoming.clinic.GetCurrentClinicUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.appointment.GetAppointmentPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic.LoadClinicPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.clinic.ClinicAdmin;

@AllArgsConstructor
public class GetCurrentClinicService implements GetCurrentClinicUseCase {
    private final LoadClinicPort loadClinicPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final GetDoctorsPort getDoctorsPort;
    private final GetAppointmentPort getAppointmentPort;


    @Override
    public GetCurrentClinicResponse getCurrentClinic() {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin admin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return clinicToResponse(loadClinicPort.loadClinic(admin.getClinic().getId()));
    }

    private GetCurrentClinicResponse clinicToResponse(Clinic clinic) {
        return new GetCurrentClinicResponse(
                clinic.getId(),
                clinic.getName(),
                clinic.getAddress().getAddressLine(),
                clinic.getAddress().getCity(),
                clinic.getAddress().getCountry(),
                clinic.getDescription(),
                clinic.getAppointmentPrices(),
                loadClinicAdminPort.loadClinicAdminsFromClinic(clinic.getId()),
                getClinicRoomsPort.getClinicRooms(clinic.getId()),
                getDoctorsPort.getDoctorsForClinic(clinic.getId()),
                getAppointmentPort.getAllByClinicId(clinic.getId()),
                clinic.getRating().getRating(),
                clinic.getRating().getCount()
        );
    }
}
