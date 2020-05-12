package org.medihub.config;

import org.medihub.application.ports.incoming.appointment_request.ScheduleAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase;
import org.medihub.application.ports.incoming.appointment_type.GetAppointmentTypesQuery;
import org.medihub.application.ports.incoming.authentication.LoginUseCase;
import org.medihub.application.ports.incoming.clinic.GetClinicNamesQuery;
import org.medihub.application.ports.incoming.clinic_admin.AddClinicAdminUseCase;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.DeleteClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomUseCase;
import org.medihub.application.ports.incoming.clinic_room.GetClinicRoomsQuery;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase;
import org.medihub.application.ports.incoming.account.GetAccountQuery;
import org.medihub.application.ports.incoming.clinic.AddClinicUseCase;
import org.medihub.application.ports.incoming.account.profile.GetProfileQuery;
import org.medihub.application.ports.incoming.account.profile.UpdateProfileUseCase;
import org.medihub.application.ports.incoming.clinic.SearchClinicsQuery;
import org.medihub.application.ports.incoming.medical_doctor.GetMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsQuery;
import org.medihub.application.ports.incoming.patient.LoadPatientUseCase;
import org.medihub.application.ports.incoming.patient.RegisterPatientUseCase;
import org.medihub.application.ports.outgoing.*;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.account.SaveAccountPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_type.GetAppointmentTypesPort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.appointment_type.SaveAppointmentTypePort;
import org.medihub.application.ports.outgoing.clinic.GetClinicByIDPort;
import org.medihub.application.ports.outgoing.clinic.GetClinicNamesPort;
import org.medihub.application.ports.outgoing.clinic.SaveClinicPort;
import org.medihub.application.ports.outgoing.clinic.SearchClinicsPort;
import org.medihub.application.ports.outgoing.clinic_room.DeleteClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.patient.SaveRegistrationRequestPort;
import org.medihub.application.ports.outgoing.authentication.AuthenticationPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.services.*;
import org.medihub.application.services.account.ChangePasswordService;
import org.medihub.application.services.account.GetAccountService;
import org.medihub.application.services.account.GetProfileService;
import org.medihub.application.services.account.UpdateProfileService;
import org.medihub.application.services.appointment.ScheduleAppointmentService;
import org.medihub.application.services.appointment_type.AddAppointmentTypeService;
import org.medihub.application.services.appointment_type.GetAppointmentTypeService;
import org.medihub.application.services.clinic.AddClinicService;
import org.medihub.application.services.clinic.GetClinicNamesService;
import org.medihub.application.services.clinic.SearchClinicsService;
import org.medihub.application.services.clinic_room.AddClinicRoomService;
import org.medihub.application.services.clinic_room.DeleteClinicRoomService;
import org.medihub.application.services.medical_doctor.AddMedicalDoctorService;
import org.medihub.application.services.medical_doctor.GetMedicalDoctorService;
import org.medihub.application.services.patient.LaodPatientService;
import org.medihub.application.services.clinic_room.GetClinicRoomsService;
import org.medihub.application.services.patient.RegisterPatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ScheduleAppointmentUseCase scheduleAppointmentUseCase(
            LoadDoctorPort loadDoctorPort,
            LoadPatientPort loadPatientPort,
            SaveAppointmentRequestPort saveAppointmentRequestPort,
            GetAuthenticatedPort getAuthenticatedPort) {
        return new ScheduleAppointmentService(
                loadDoctorPort,
                loadPatientPort,
                saveAppointmentRequestPort,
                getAuthenticatedPort);
    }

    @Bean
    public GetClinicRoomsQuery getClinicRoomsQuery(
            GetClinicRoomsPort getClinicRoomsPort) {
        return new GetClinicRoomsService(getClinicRoomsPort);
    }

    @Bean
    public GetDoctorsQuery getDoctorsQuery(
            GetDoctorsPort getDoctorsPort) {
        return new GetDoctorsService(getDoctorsPort);
    }

    @Bean
    public GetAppointmentTypesQuery getAppointmentTypesQuery(
            GetAppointmentTypesPort getAppointmentTypesPort) {
        return new GetAppointmentTypeService(getAppointmentTypesPort);
    }

    @Bean
    public LoginUseCase getLoginUseCase(
            AuthenticationPort authenticationPort,
            LoadClinicAdminPort loadClinicAdminPort) {
        return new LoginService(authenticationPort, loadClinicAdminPort);
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
    public AddClinicRoomUseCase getClinicRoomUseCase(
            SaveClinicRoomPort saveClinicRoomPort,
            GetClinicByIDPort getClinicByIDPort) {
        return new AddClinicRoomService(
                saveClinicRoomPort,
                getClinicByIDPort);
    }

    @Bean
    public GetClinicRoomUseCase getClinicRoomUseCase(GetClinicRoomsPort getClinicRoomsPort){
        return new GetClinicRoomsService(getClinicRoomsPort);
    }

    @Bean
    public AddMedicalDoctorUseCase getAddDoctorUseCase(
            SaveDoctorPort saveDoctorPorts,
            EncoderPort encoderPort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort,
            LoadAppointmentTypePort loadAppointmentTypePort){
        return new AddMedicalDoctorService(
                saveDoctorPorts,
                encoderPort,
                getAuthenticatedPort,
                loadClinicAdminPort,
                loadAppointmentTypePort);
    }

    @Bean
    public GetMedicalDoctorUseCase getMedicalDoctorUseCase(GetAllDoctorsPort doctorPort){
        return new GetMedicalDoctorService(
                doctorPort
        );
    }

    @Bean
    public AddAppointmentTypeUseCase getAddAppointmentTypeUseCase(SaveAppointmentTypePort saveAppointmentTypePort){
        return new AddAppointmentTypeService(saveAppointmentTypePort);
    }

    @Bean
    public DeleteClinicRoomUseCase getDeleteClinicRoomUseCase(
            DeleteClinicRoomPort deleteClinicRoomPort,
            LoadClinicRoomPort loadClinicRoomPort){
        return new DeleteClinicRoomService(deleteClinicRoomPort, loadClinicRoomPort);
    }

    @Bean
    public LoadPatientUseCase getLoadPatientPort(GetPatientsPort getPatientsPort){
        return new LaodPatientService(getPatientsPort);
    }

    @Bean
    public GetClinicNamesQuery getClinicNamesQuery(GetClinicNamesPort getClinicNamesPort){
        return new GetClinicNamesService(getClinicNamesPort);
    }
}
