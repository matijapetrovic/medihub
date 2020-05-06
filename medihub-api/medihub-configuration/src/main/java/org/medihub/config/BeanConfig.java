package org.medihub.config;

import org.medihub.application.ports.incoming.*;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase;
import org.medihub.application.ports.incoming.appointment_type.GetAppointmentTypesQuery;
import org.medihub.application.ports.incoming.authentication.LoginUseCase;
import org.medihub.application.ports.incoming.clinic.GetClinicNamesQuery;
import org.medihub.application.ports.incoming.clinic_admin.AddClinicAdminUseCase;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.DeleteClinicRoomUseCase;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase;
import org.medihub.application.ports.incoming.account.GetAccountQuery;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase;
import org.medihub.application.ports.incoming.account.profile.GetProfileQuery;
import org.medihub.application.ports.incoming.account.profile.UpdateProfileUseCase;
import org.medihub.application.ports.incoming.clinic.SearchClinicsQuery;
import org.medihub.application.ports.incoming.patient.LoadPatientUseCase;
import org.medihub.application.ports.incoming.patient.RegisterPatientUseCase;
import org.medihub.application.ports.outgoing.*;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.account.SaveAccountPort;
import org.medihub.application.ports.outgoing.appointment_type.GetAppointmentTypesPort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.appointment_type.SaveAppointmentTypePort;
import org.medihub.application.ports.outgoing.clinic.GetClinicByIDPort;
import org.medihub.application.ports.outgoing.clinic.GetClinicNamesPort;
import org.medihub.application.ports.outgoing.clinic.SaveClinicPort;
import org.medihub.application.ports.outgoing.clinic.SearchClinicsPort;
import org.medihub.application.ports.outgoing.clinic_room.DeleteClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.application.ports.outgoing.patient.SaveRegistrationRequestPort;
import org.medihub.application.ports.outgoing.áuthentication.AuthenticationPort;
import org.medihub.application.ports.outgoing.áuthentication.GetAuthenticatedPort;
import org.medihub.application.services.*;
import org.medihub.application.services.account.ChangePasswordService;
import org.medihub.application.services.account.GetAccountService;
import org.medihub.application.services.account.GetProfileService;
import org.medihub.application.services.account.UpdateProfileService;
import org.medihub.application.services.appointment_type.AddAppointmentTypeService;
import org.medihub.application.services.appointment_type.GetAppointmentTypeService;
import org.medihub.application.services.clinic.AddClinicService;
import org.medihub.application.services.clinic.GetClinicNamesService;
import org.medihub.application.services.clinic.SearchClinicsService;
import org.medihub.application.services.patient.RegisterPatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GetAppointmentTypesQuery getAppointmentTypesQuery(
            GetAppointmentTypesPort getAppointmentTypesPort) {
        return new GetAppointmentTypeService(getAppointmentTypesPort);
    }

    @Bean
    public LoginUseCase getLoginUseCase(AuthenticationPort authenticationPort) {
        return new LoginService(authenticationPort);
    }

    @Bean
    public SearchClinicsQuery searchClinicsQuery(
            LoadAppointmentTypePort loadAppointmentTypePort,
            SearchClinicsPort searchClinicsPort) {
        return new SearchClinicsService(loadAppointmentTypePort, searchClinicsPort);
    }

    @Bean
    public ChangePasswordUseCase changePasswordUseCase(
            AuthenticationPort authenticationPort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadAccountPort loadAccountPort,
            EncoderPort encoderPort,
            SaveAccountPort saveAccountPort) {
        return new ChangePasswordService(
                authenticationPort,
                getAuthenticatedPort,
                loadAccountPort,
                encoderPort,
                saveAccountPort);
    }

    @Bean
    public AddClinicUseCase registerClinicUseCase(
            SaveClinicPort saveClinicPort) {
        return new AddClinicService(saveClinicPort);
    }

    @Bean
    public UpdateProfileUseCase updateProfileUseCase(
            LoadAccountPort loadAccountPort,
            SaveAccountPort saveAccountPort) {
        return new UpdateProfileService(loadAccountPort, saveAccountPort);
    }

    @Bean
    public GetProfileQuery getProfileQuery(LoadAccountPort loadAccountPort) {
        return new GetProfileService(loadAccountPort);
    }

    @Bean
    public RegisterPatientUseCase registerPatientUseCase(
            LoadAccountPort loadAccountPort,
            SaveRegistrationRequestPort saveRegistrationRequestPort,
            EncoderPort encoderPort
    ) {
        return new RegisterPatientService(loadAccountPort,saveRegistrationRequestPort, encoderPort);
    }

    @Bean
    public GetAccountQuery getAccountQuery(LoadAccountPort loadAccountPort) {
        return new GetAccountService(loadAccountPort);
    }

    @Bean
    public AddClinicAdminUseCase getAddClinicAdminUseCase(
            LoadAccountPort loadAccountPort,
            AddClinicAdminPort addClinicAdminPort,
            EncoderPort encoderPort,
            GetClinicByIDPort getClinicByIDPort
    ) {
        return new AddClinicAdminService(addClinicAdminPort, loadAccountPort, encoderPort, getClinicByIDPort);
    }

    @Bean
    public AddClinicRoomUseCase getClinicRoomUseCase(SaveClinicRoomPort saveClinicRoomPort){
        return new AddClinicRoomService(
                saveClinicRoomPort
        );
    }

    @Bean
    public AddMedicalDoctorUseCase getAddDoctorUseCase(SaveDoctorPort saveDoctorPorts, EncoderPort encoderPort){
        return new AddMedicalDoctorService(
                saveDoctorPorts,
                encoderPort
        );
    }

    @Bean
    public AddAppointmentTypeUseCase getAddAppointmentTypeUseCase(SaveAppointmentTypePort saveAppointmentTypePort){
        return new AddAppointmentTypeService(saveAppointmentTypePort);
    }

    @Bean
    public DeleteClinicRoomUseCase getDeleteClinicRoomUseCase(DeleteClinicRoomPort deleteClinicRoomPort){
        return new DeleteClinicRoomService(deleteClinicRoomPort);
    }

    @Bean
    public LoadPatientUseCase getLoadPatientPort(LoadPatientPort loadPatientPort){
        return new LaodPatientService(loadPatientPort);
    }

    @Bean
    public GetClinicNamesQuery getClinicNamesQuery(GetClinicNamesPort getClinicNamesPort){
        return new GetClinicNamesService(getClinicNamesPort);
    }
}
