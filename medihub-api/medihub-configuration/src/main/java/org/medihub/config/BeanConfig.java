package org.medihub.config;

import org.medihub.application.ports.incoming.account.ActivateAccountUseCase;
import org.medihub.application.ports.incoming.appointment.CancelAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.GetAppointmentsQuery;
import org.medihub.application.ports.incoming.appointment.GetCurrentAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment_request.GetAllAppointmentRequestsUseCase;
import org.medihub.application.ports.incoming.appointment_type.ChangeAppointmentTypeUseCase;
import org.medihub.application.ports.incoming.clinic.*;
import org.medihub.application.ports.incoming.diagnosis.GetDiagnosisQuery;
import org.medihub.application.ports.incoming.drugs.GetDrugsQuery;
import org.medihub.application.ports.incoming.finished_appointment.*;
import org.medihub.application.ports.incoming.clinic.GetAppointmentPriceUseCase;
import org.medihub.application.ports.incoming.finished_appointment.GetAppointmentHistoryQuery;
import org.medihub.application.ports.incoming.finished_appointment.GetFinishedAppointmentProfitUseCase;
import org.medihub.application.ports.incoming.leave_request.AddLeaveRequestUseCase;
import org.medihub.application.ports.incoming.leave_request.ApproveLeaveRequestUseCase;
import org.medihub.application.ports.incoming.leave_request.DeleteLeaveRequestUseCase;
import org.medihub.application.ports.incoming.leave_request.GetLeaveRequestUseCase;
import org.medihub.application.ports.incoming.medical_doctor.schedule.GetDoctorScheduleQuery;
import org.medihub.application.ports.incoming.medical_record.ChangeMedicalRecordUseCase;
import org.medihub.application.ports.incoming.medical_record.GetBloodTypesQuery;
import org.medihub.application.ports.incoming.medical_record.GetPatientMedicalRecordQuery;
import org.medihub.application.ports.incoming.operation.AddOperationUseCase;
import org.medihub.application.ports.incoming.predefined_appointment.AddPredefinedAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment_request.DeleteAppointmentRequestUseCase;
import org.medihub.application.ports.incoming.appointment_request.GetAppointmentRequestForClinicUseCase;
import org.medihub.application.ports.incoming.clinic_room.*;
import org.medihub.application.ports.incoming.diagnosis.AddDiagnosisUseCase;
import org.medihub.application.ports.incoming.drugs.AddDrugUseCase;
import org.medihub.application.ports.incoming.medical_doctor.*;
import org.medihub.application.ports.incoming.medical_record.GetMedicalRecordQuery;
import org.medihub.application.ports.incoming.predefined_appointment.GetAllPredefinedAppointmentsUseCase;
import org.medihub.application.ports.incoming.predefined_appointment.GetPredefinedAppointmentsQuery;
import org.medihub.application.ports.incoming.prescription.AcceptPrescriptionUseCase;
import org.medihub.application.ports.incoming.prescription.GetPrescriptionsQuery;
import org.medihub.application.ports.incoming.registration.AcceptRegistrationUseCase;
import org.medihub.application.ports.incoming.registration.RejectRegistrationUseCase;
import org.medihub.application.ports.incoming.reviewing.AddClinicReviewUseCase;
import org.medihub.application.ports.incoming.reviewing.AddDoctorReviewUseCase;
import org.medihub.application.ports.incoming.scheduling.*;
import org.medihub.application.ports.incoming.reviewing.GetClinicsForReviewQuery;
import org.medihub.application.ports.incoming.reviewing.GetDoctorsForReviewQuery;
import org.medihub.application.ports.incoming.scheduling.GetDoctorAvailableTimesQuery;
import org.medihub.application.ports.incoming.scheduling.ScheduleAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase;
import org.medihub.application.ports.incoming.appointment_type.GetAppointmentTypesQuery;
import org.medihub.application.ports.incoming.appointment_type.DeleteAppointmentTypeUseCase;
import org.medihub.application.ports.incoming.authentication.LoginUseCase;
import org.medihub.application.ports.incoming.clinic_admin.AddClinicAdminUseCase;
import org.medihub.application.ports.incoming.medical_doctor.AddMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase;
import org.medihub.application.ports.incoming.account.GetAccountQuery;
import org.medihub.application.ports.incoming.profile.GetProfileQuery;
import org.medihub.application.ports.incoming.profile.UpdateProfileUseCase;
import org.medihub.application.ports.incoming.clinic.SearchClinicsQuery;
import org.medihub.application.ports.incoming.medical_doctor.GetMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.medical_doctor.GetDoctorsQuery;
import org.medihub.application.ports.incoming.registration.GetRegistrationRequestsQuery;
import org.medihub.application.ports.incoming.patient.LoadPatientUseCase;
import org.medihub.application.ports.incoming.registration.RegisterPatientUseCase;
import org.medihub.application.ports.outgoing.*;
import org.medihub.application.ports.outgoing.account.DeleteAccountPort;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.account.SaveAccountPort;
import org.medihub.application.ports.outgoing.appointment.*;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAllAppointmentRequestsPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAppointmentRequestForClinicPort;
import org.medihub.application.ports.outgoing.appointment_type.*;
import org.medihub.application.ports.outgoing.clinic.*;
import org.medihub.application.ports.outgoing.clinic_room.*;
import org.medihub.application.ports.outgoing.clinic_room_schedule.LoadClinicRoomSchedulePort;
import org.medihub.application.ports.outgoing.clinic_room_schedule.ScheduleClinicRoomPort;
import org.medihub.application.ports.outgoing.diagnosis.GetDiagnosisByIdPort;
import org.medihub.application.ports.outgoing.diagnosis.GetDiagnosisPort;
import org.medihub.application.ports.outgoing.diagnosis.SaveDiagnosisPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.doctor.SaveDoctorPort;
import org.medihub.application.ports.outgoing.clinic_room.DeleteClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.application.ports.outgoing.doctor.*;
import org.medihub.application.ports.outgoing.drugs.GetDrugByIdPort;
import org.medihub.application.ports.outgoing.drugs.GetDrugsPort;
import org.medihub.application.ports.outgoing.drugs.SaveDrugPort;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.medihub.application.ports.outgoing.finished_appointment.*;
import org.medihub.application.ports.outgoing.leave_request.AddLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.ApproveLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.DeleteLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.medihub.application.ports.outgoing.medical_nurse.GetMedicalNurseByAccountIdPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordByIdPort;
import org.medihub.application.ports.outgoing.medical_record.LoadMedicalRecordPort;
import org.medihub.application.ports.outgoing.medical_record.SaveMedicalRecordPort;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.application.ports.outgoing.patient.SavePatientPort;
import org.medihub.application.ports.outgoing.registration_request.DeleteRegistrationRequestPort;
import org.medihub.application.ports.outgoing.registration_request.GetRegistrationRequestsPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.registration_request.LoadRegistrationRequestPort;
import org.medihub.application.ports.outgoing.registration_request.SaveRegistrationRequestPort;
import org.medihub.application.ports.outgoing.authentication.AuthenticationPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.predefined_appointment.*;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionsPort;
import org.medihub.application.ports.outgoing.profile.LoadPersonalInfoPort;
import org.medihub.application.ports.outgoing.profile.SavePersonalInfoPort;
import org.medihub.application.ports.outgoing.reviewing.*;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.SaveClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.SaveDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.DeleteMedicalDoctorScheduleItemPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorScheduleItemPort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalDoctorScheduleItemPort;
import org.medihub.application.services.*;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.application.services.account.ActivateAccountService;
import org.medihub.application.services.account.ChangePasswordService;
import org.medihub.application.services.account.GetAccountService;
import org.medihub.application.services.profile.GetProfileService;
import org.medihub.application.services.profile.UpdateProfileService;
import org.medihub.application.services.appointment.CancelAppointmentService;
import org.medihub.application.services.appointment.GetAppointmentsService;
import org.medihub.application.services.appointment.GetCurrentAppointmentService;
import org.medihub.application.services.appointment_request.get.GetAllAppointmentRequestsService;
import org.medihub.application.services.appointment_type.change.ChangeAppointmentTypeService;
import org.medihub.application.services.clinic.get.GetClinicProfileService;
import org.medihub.application.services.clinic.add.AddPriceService;
import org.medihub.application.services.clinic.get.GetCurrentClinicService;
import org.medihub.application.services.clinic.put.UpdateClinicService;
import org.medihub.application.services.clinic_room.get.*;
import org.medihub.application.services.clinic_room.update.UpdateClinicRoomService;
import org.medihub.application.services.clinic_room.add.AddClinicRoomService;
import org.medihub.application.services.clinic_room.add.ScheduleClinicRoomService;
import org.medihub.application.services.clinic_room.delete.DeleteClinicRoomService;
import org.medihub.application.services.finished_appointment.*;
import org.medihub.application.services.leave_request.add.AddLeaveRequestService;
import org.medihub.application.services.leave_request.add.ApproveLeaveRequestService;
import org.medihub.application.services.leave_request.delete.DeleteLeaveRequestService;
import org.medihub.application.services.leave_request.get.GetLeaveRequestService;
import org.medihub.application.services.medical_doctor.DeleteDoctorService;
import org.medihub.application.services.medical_doctor.add.AddAppointmentToMedicalDoctorService;
import org.medihub.application.services.medical_doctor.get.*;
import org.medihub.application.services.clinic.get.GetAppointmentPriceService;
import org.medihub.application.services.diagnosis.GetDiagnosisService;
import org.medihub.application.services.drugs.GetDrugsService;
import org.medihub.application.services.medical_record.ChangeMedicalRecordService;
import org.medihub.application.services.medical_record.GetBloodTypesService;
import org.medihub.application.services.medical_record.GetPatientMedicalRecordService;
import org.medihub.application.services.predefined_appointment.AddPredefinedAppointmentService;
import org.medihub.application.services.appointment.AddAppointmentService;
import org.medihub.application.services.appointment_request.delete.DeleteAppointmentRequestService;
import org.medihub.application.services.appointment_request.get.GetAppointmentRequestForClinicService;
import org.medihub.application.services.appointment_type.add.AddAppointmentTypeService;
import org.medihub.application.services.diagnosis.AddDiagnosisService;
import org.medihub.application.services.drugs.AddDrugService;
import org.medihub.application.services.medical_record.GetMedicalRecordService;
import org.medihub.application.services.predefined_appointment.GetAllPredefinedAppointmentsService;
import org.medihub.application.services.predefined_appointment.GetPredefinedAppointmentsOutputService;
import org.medihub.application.services.prescription.AcceptPrescriptionService;
import org.medihub.application.services.prescription.GetPrescriptionsService;
import org.medihub.application.services.registration.AcceptRegistrationService;
import org.medihub.application.services.registration.RejectRegistrationService;
import org.medihub.application.services.reviewing.GetClinicsForReviewService;
import org.medihub.application.services.reviewing.GetDoctorsForReviewService;
import org.medihub.application.services.scheduling.add.ScheduleDoctorsAppointmentService;
import org.medihub.application.services.scheduling.add.SchedulePredefinedAppointmentService;
import org.medihub.application.services.scheduling.get.GetDoctorAvailableTimesService;
import org.medihub.application.services.scheduling.add.ScheduleAppointmentService;
import org.medihub.application.services.appointment_type.get.GetAppointmentTypeService;
import org.medihub.application.services.appointment_type.delete.DeleteAppointmentTypeService;
import org.medihub.application.services.clinic.add.AddClinicService;
import org.medihub.application.services.clinic.get.GetClinicNamesService;
import org.medihub.application.services.clinic.get.SearchClinicsService;
import org.medihub.application.services.medical_doctor.add.AddMedicalDoctorService;
import org.medihub.application.services.patient.LoadPatientService;
import org.medihub.application.services.registration.RegisterPatientService;
import org.medihub.application.services.reviewing.AddClinicReviewService;
import org.medihub.application.services.reviewing.AddDoctorReviewService;
import org.medihub.application.services.scheduling.get.GetMedicalDoctorAppointmentScheduleItemService;
import org.medihub.application.services.registration.GetRegistrationRequestsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DeleteDoctorUseCase deleteDoctorUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort,
            LoadDoctorPort loadDoctorPort,
            LoadMedicalDoctorScheduleItemPort loadMedicalDoctorScheduleItemPort,
            SaveDoctorPort saveDoctorPort,
            DeleteAccountPort deleteAccountPort
    ) {
        return new DeleteDoctorService(
                getAuthenticatedPort,
                loadClinicAdminPort,
                loadDoctorPort,
                loadMedicalDoctorScheduleItemPort,
                saveDoctorPort,
                deleteAccountPort);
    }

    @Bean
    public GetClinicsForReviewQuery getClinicsForReviewQuery(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadPatientPort loadPatientPort,
            GetClinicReviewsPort getClinicReviewsPort) {
        return new GetClinicsForReviewService(
                getAuthenticatedPort,
                loadPatientPort,
                getClinicReviewsPort);
    }

    @Bean
    public GetDoctorsForReviewQuery getDoctorsForReviewQuery(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadPatientPort loadPatientPort,
            GetDoctorReviewsPort getDoctorReviewsPort) {
        return new GetDoctorsForReviewService(
                getAuthenticatedPort,
                loadPatientPort,
                getDoctorReviewsPort);
    }

    @Bean
    public SchedulePredefinedAppointmentUseCase schedulePredefinedAppointmentUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadPatientPort loadPatientPort,
            LoadPredefinedAppointmentPort loadPredefinedAppointmentPort,
            SaveAppointmentPort saveAppointmentPort,
            LoadMedicalDoctorScheduleItemPort loadMedicalDoctorScheduleItemPort,
            SaveMedicalDoctorScheduleItemPort saveMedicalDoctorScheduleItemPort,
            DeleteMedicalDoctorScheduleItemPort deleteMedicalDoctorScheduleItemPort,
            DeletePredefinedAppointmentPort deletePredefinedAppointmentPort,
            SendEmailPort sendEmailPort) {
        return new SchedulePredefinedAppointmentService(
                getAuthenticatedPort,
                loadPatientPort,
                loadPredefinedAppointmentPort,
                saveAppointmentPort,
                loadMedicalDoctorScheduleItemPort,
                deleteMedicalDoctorScheduleItemPort,
                saveMedicalDoctorScheduleItemPort,
                deletePredefinedAppointmentPort,
                sendEmailPort);
    }

    @Bean
    public GetPredefinedAppointmentsQuery getPredefinedAppointmentsQuery(
            GetPredefinedAppointmentsPort getPredefinedAppointmentsPort) {
        return new GetPredefinedAppointmentsOutputService(getPredefinedAppointmentsPort);
    }

    @Bean
    public CancelAppointmentUseCase cancelAppointmentUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadAppointmentPort loadAppointmentPort,
            DeleteAppointmentScheduleItemPort deleteAppointmentScheduleItemPort) {
        return new CancelAppointmentService(
                getAuthenticatedPort,
                loadAppointmentPort,
                deleteAppointmentScheduleItemPort);
    }

    @Bean
    public GetAppointmentsQuery getAppointmentsQuery(
        GetAuthenticatedPort getAuthenticatedPort,
        LoadPatientPort loadPatientPort,
        GetScheduledAppointmentsPort getScheduledAppointmentsPort) {
        return new GetAppointmentsService(
                getAuthenticatedPort, loadPatientPort, getScheduledAppointmentsPort);
    }

    @Bean
    public AddDoctorReviewUseCase addDoctorReviewUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadPatientPort loadPatientPort,
            SaveDoctorReviewPort saveDoctorReviewPort,
            LoadDoctorReviewPort loadDoctorReviewPort) {
        return new AddDoctorReviewService(
                getAuthenticatedPort,
                loadPatientPort,
                saveDoctorReviewPort,
                loadDoctorReviewPort);
    }

    @Bean
    public GetAppointmentHistoryQuery getAppointmentHistoryQuery(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadPatientPort loadPatientPort,
            GetFinishedAppointmentsPort getFinishedAppointmentsPort,
            LoadClinicReviewPort loadClinicReviewPort,
            LoadDoctorReviewPort loadDoctorReviewPort,
            LoadClinicAdminPort loadClinicAdminPort
            ) {
        return new GetAppointmentHistoryService(
                getAuthenticatedPort,
                loadPatientPort,
                getFinishedAppointmentsPort,
                loadClinicReviewPort,
                loadDoctorReviewPort,
                loadClinicAdminPort);
    }

    @Bean
    public AddClinicReviewUseCase addClinicReviewUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadPatientPort loadPatientPort,
            SaveClinicReviewPort saveClinicReviewPort,
            LoadClinicReviewPort loadClinicReviewPort) {
        return new AddClinicReviewService(
                getAuthenticatedPort,
                loadPatientPort,
                saveClinicReviewPort,
                loadClinicReviewPort);
    }

    @Bean
    public GetMedicalRecordQuery getMedicalRecordQuery(
            LoadMedicalRecordPort loadMedicalRecordPort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadPatientPort loadPatientPort,
            GetFinishedAppointmentsPort getFinishedAppointmentsPort) {
        return new GetMedicalRecordService(
                loadMedicalRecordPort,
                getAuthenticatedPort,
                loadPatientPort,
                getFinishedAppointmentsPort);
    }

    @Bean
    public GetDoctorAvailableTimesQuery getDoctorAvailableTimesQuery(
            LoadDoctorDailySchedulePort loadDoctorDailySchedulePort,
            GetDoctorWorkingTimePort getDoctorWorkingTimePort) {
        return new GetDoctorAvailableTimesService(
                loadDoctorDailySchedulePort,
                getDoctorWorkingTimePort);
    }

    @Bean
    public SearchDoctorsQuery searchDoctorsQuery(
        SearchDoctorsPort searchDoctorsPort,
        LoadDoctorDailySchedulePort loadDoctorDailySchedulePort) {
        return new SearchDoctorsService(searchDoctorsPort, loadDoctorDailySchedulePort);
    }

    @Bean
    public ScheduleAppointmentUseCase scheduleAppointmentUseCase(
            LoadDoctorPort loadDoctorPort,
            LoadPatientPort loadPatientPort,
            SaveAppointmentRequestPort saveAppointmentRequestPort,
            GetAuthenticatedPort getAuthenticatedPort,
            SendEmailPort sendEmailPort) {
        return new ScheduleAppointmentService(
                loadDoctorPort,
                loadPatientPort,
                saveAppointmentRequestPort,
                getAuthenticatedPort,
                sendEmailPort);
    }

    @Bean
    public ScheduleDoctorsAppointmentUseCase scheduleDoctorsAppointmentUseCase(
            LoadDoctorPort loadDoctorPort,
            LoadPatientPort loadPatientPort,
            SaveAppointmentRequestPort saveAppointmentRequestPort,
            GetAuthenticatedPort getAuthenticatedPort) {
        return new ScheduleDoctorsAppointmentService(
                loadDoctorPort,
                loadPatientPort,
                saveAppointmentRequestPort,
                getAuthenticatedPort);
    }

    @Bean
    public GetClinicRoomsForClinicQuery getClinicRoomsQuery(
            GetClinicRoomsPort getClinicRoomsPort) {
        return new GetClinicRoomsForClinicService(getClinicRoomsPort);
    }

    @Bean
    public GetClinicRoomScheduleQuery getClinicRoomScheduleQuery(
            LoadClinicRoomSchedulePort loadClinicRoomSchedulePort
    ) {
        return new GetClinicRoomScheduleService(loadClinicRoomSchedulePort);
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
    public UpdateClinicUseCase updateClinicUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort,
            LoadClinicPort loadClinicPort,
            SaveClinicPort saveClinicPort
    ) {
        return new UpdateClinicService(
                getAuthenticatedPort,
                loadClinicAdminPort,
                loadClinicPort,
                saveClinicPort
                );
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
            LoadPersonalInfoPort loadPersonalInfoPort,
            SavePersonalInfoPort savePersonalInfoPort) {
        return new UpdateProfileService(
                loadAccountPort,
                loadPersonalInfoPort,
                savePersonalInfoPort);
    }

    @Bean
    public GetProfileQuery getProfileQuery(LoadAccountPort loadAccountPort, LoadPersonalInfoPort loadPersonalInfoPort) {
        return new GetProfileService(loadAccountPort, loadPersonalInfoPort);
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
            LoadClinicPort loadClinicPort
    ) {
        return new AddClinicAdminService(addClinicAdminPort, loadAccountPort, encoderPort, loadClinicPort);
    }

    @Bean
    public AddClinicRoomUseCase getClinicRoomUseCase(
            SaveClinicRoomPort saveClinicRoomPort,
            LoadClinicPort loadClinicPort) {
        return new AddClinicRoomService(
                saveClinicRoomPort,
                loadClinicPort);
    }

    @Bean
    public UpdateClinicRoomUseCase updateClinicRoomUseCase(
            LoadClinicRoomPort loadClinicRoomPort,
            SaveClinicRoomPort saveClinicRoomPort
    ) {
        return new UpdateClinicRoomService(
                loadClinicRoomPort,
                saveClinicRoomPort);
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
    public GetAllClinicMedicalDoctorsUseCase getMedicalDoctorUseCase(
            GetDoctorsPort getDoctorsPort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort){
        return new GetMedicalDoctorForClinicService(
                getDoctorsPort,
                getAuthenticatedPort,
                loadClinicAdminPort
        );
    }

    @Bean
    public GetAllMedicalDoctorsUseCase getAllMedicalDoctorsUseCase(GetAllDoctorsPort getAllDoctorsPort) {
        return new GetAllDoctorsService(getAllDoctorsPort);
    }

    @Bean
    public AddAppointmentTypeUseCase getAddAppointmentTypeUseCase(SaveAppointmentTypePort saveAppointmentTypePort){
        return new AddAppointmentTypeService(saveAppointmentTypePort);
    }

    @Bean
    public GetCurrentAppointmentUseCase getCurrentAppointmentUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            GetDoctorByAccountIdPort getDoctorByAccountIdPort,
            GetCurrentAppointmentPort getCurrentAppointmentPort
    ) {
        return new GetCurrentAppointmentService(
                getAuthenticatedPort,
                getDoctorByAccountIdPort,
                getCurrentAppointmentPort);
    }

    @Bean
    public DeleteClinicRoomUseCase getDeleteClinicRoomUseCase(
            DeleteClinicRoomPort deleteClinicRoomPort,
            LoadClinicRoomPort loadClinicRoomPort){
        return new DeleteClinicRoomService(deleteClinicRoomPort, loadClinicRoomPort);
    }

    @Bean GetAllClinicRoomsUseCase getAllClinicRoomsUseCase(GetAllClinicRoomsPort getAllClinicRoomsPort) {
        return new GetAllClinicRoomsService(getAllClinicRoomsPort);
    }

    @Bean
    public GetRoomAvailableTimeQuery getRoomAvailableTimeQuery(LoadRoomDailySchedulePort loadRoomDailySchedulePort) {
        return new GetRoomAvailableTimesService(loadRoomDailySchedulePort);
    }

    @Bean
    public LoadPatientUseCase getLoadPatientPort(GetPatientsPort getPatientsPort){
        return new LoadPatientService(getPatientsPort);
    }

    @Bean
    public GetClinicNamesQuery getClinicNamesQuery(GetClinicNamesPort getClinicNamesPort){
        return new GetClinicNamesService(getClinicNamesPort);
    }

    @Bean
    public SearchClinicRoomsQuery getSearchClinicRoomsQuery(
            SearchClinicRoomsPort searchClinicRoomsPort,
            LoadClinicRoomSchedulePort loadClinicRoomSchedulePort,
            GetAuthenticatedPort loadAccountPort,
            LoadClinicAdminPort loadClinicAdminPort){
        return new SearchClinicRoomsService(
                searchClinicRoomsPort,
                loadClinicRoomSchedulePort,
                loadAccountPort,
                loadClinicAdminPort);
    }

    @Bean
    public DeleteAppointmentTypeUseCase removeAppointmentTypeQuery(DeleteAppointmentTypePort deleteAppointmentTypePort) {
        return new DeleteAppointmentTypeService(deleteAppointmentTypePort);
    }

    @Bean
    public AddPredefinedAppointmentUseCase geAddPredefinedAppointmentUseCase(
            AddPredefinedAppointmentPort addPredefinedAppointmentPort,
            GetClinicRoomsPort getClinicRoomsPort,
            GetAppointmentTypesPort getAppointmentTypesPort,
            GetDoctorsPort getDoctorsPort,
            LoadDoctorDailySchedulePort loadDoctorDailySchedulePort,
            SaveDoctorDailySchedulePort saveDoctorDailySchedulePort,
            LoadClinicRoomDailySchedulePort loadClinicRoomDailySchedulePort,
            SaveClinicRoomDailySchedulePort saveClinicRoomDailySchedulePort
    ) {
        return new AddPredefinedAppointmentService(
                addPredefinedAppointmentPort,
                getClinicRoomsPort,
                getAppointmentTypesPort,
                getDoctorsPort,
                loadDoctorDailySchedulePort,
                saveDoctorDailySchedulePort,
                loadClinicRoomDailySchedulePort,
                saveClinicRoomDailySchedulePort);
    }

    @Bean
    public GetAllPredefinedAppointmentsUseCase getAllPredefinedAppointmentsUseCase(
            GetAllPredefinedAppointmentsPort getAllPredefinedAppointmentsPort) {
        return new GetAllPredefinedAppointmentsService(getAllPredefinedAppointmentsPort);
    }
    @Bean
    public GetAppointmentRequestForClinicUseCase getAppointmentRequestUseCase(
            GetAppointmentRequestForClinicPort getAppointmentRequestForClinicPort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort) {
        return new GetAppointmentRequestForClinicService(
                getAppointmentRequestForClinicPort,
                getAuthenticatedPort,
                loadClinicAdminPort);
    }

    @Bean
    public GetAllAppointmentRequestsUseCase getAllAppointmentRequestsUseCase(
            GetAllAppointmentRequestsPort getAllAppointmentRequestsPort) {
        return new GetAllAppointmentRequestsService(getAllAppointmentRequestsPort);
    }

    @Bean
    public DeleteAppointmentRequestUseCase getDeleteAppointmentRequestUseCase(
            DeleteAppointmentRequestPort deleteAppointmentRequestPort) {
        return new DeleteAppointmentRequestService(deleteAppointmentRequestPort);
    }

    @Bean
    public GetAppointmentPriceUseCase getAppointmentPriceUseCase(
            GetAppointmentPricePort getAppointmentPricePort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort) {
        return new GetAppointmentPriceService(
                getAppointmentPricePort,
                getAuthenticatedPort,
                loadClinicAdminPort);
    }

    @Bean
    public ScheduleClinicRoomUseCase getScheduleClinicRoomUseCase(ScheduleClinicRoomPort scheduleClinicRoomPort) {
        return new ScheduleClinicRoomService(scheduleClinicRoomPort);
    }


    @Bean
    public GetDoctorScheduleQuery getDoctorScheduleQuery(
            GetDoctorSchedulePort getDoctorSchedulePort,
            GetAuthenticatedPort getAuthenticatedPort,
            GetDoctorByAccountIdPort getDoctorByAccountIdPort) {
        return new GetDoctorScheduleService(getDoctorSchedulePort, getAuthenticatedPort, getDoctorByAccountIdPort);
    }

    @Bean
    public AddAppointmentToMedicalDoctorScheduleUseCase getAddAppointmentToMedicalDoctorScheduleUseCase(
            AddAppointmentToMedicalDoctorSchedulePort addAppointmentToMedicalDoctorSchedulePort) {
        return new AddAppointmentToMedicalDoctorService(addAppointmentToMedicalDoctorSchedulePort);
    }

    @Bean
    public AddAppointmentUseCase getAddAppointmentUseCase(
            SaveAppointmentPort saveAppointmentPort,
            GetDoctorsPort getDoctorsPort,
            GetPatientsPort getPatientsPort,
            GetClinicRoomsPort getClinicRoomsPort,
            AddAppointmentToMedicalDoctorSchedulePort addAppointmentToMedicalDoctorSchedulePort,
            AddAppointmentToClinicRoomPort addAppointmentToClinicRoomPort
    ) {
        return new AddAppointmentService(
                saveAppointmentPort,
                getDoctorsPort,
                getPatientsPort,
                getClinicRoomsPort,
                addAppointmentToMedicalDoctorSchedulePort,
                addAppointmentToClinicRoomPort
        );
    }

    @Bean
    public AddDrugUseCase addDrugUseCase(SaveDrugPort saveDrugPort) {
        return new AddDrugService(saveDrugPort);
    }

    @Bean
    public AddDiagnosisUseCase addDiagnosisUseCase(SaveDiagnosisPort saveDiagnosisPort) {
        return new AddDiagnosisService(saveDiagnosisPort);
    }

    @Bean
    public AddLeaveRequestUseCase addLeaveRequestUseCase(
            AddLeaveRequestPort addLeaveRequestPort,
            GetDoctorByAccountIdPort getDoctorByAccountIdPort,
            GetAuthenticatedPort getDoctorsPort) {
        return new AddLeaveRequestService(
                addLeaveRequestPort,
                getDoctorByAccountIdPort,
                getDoctorsPort);
    }

    @Bean
    public GetLeaveRequestUseCase getLeaveRequestUseCase(GetLeaveRequestPort getLeaveRequestPort) {
        return new GetLeaveRequestService(getLeaveRequestPort);
    }

    @Bean
    public DeleteLeaveRequestUseCase deleteLeaveRequestUseCase(DeleteLeaveRequestPort deleteLeaveRequestPort) {
        return new DeleteLeaveRequestService(deleteLeaveRequestPort);
    }

    @Bean
    public ApproveLeaveRequestUseCase approveLeaveRequestUseCase(
            ApproveLeaveRequestPort approveLeaveRequestPort,
            GetLeaveRequestPort getLeaveRequestPort,
            DeleteLeaveRequestPort deleteLeaveRequestPort) {
        return new ApproveLeaveRequestService(
                approveLeaveRequestPort,
                getLeaveRequestPort,
                deleteLeaveRequestPort);
    }

    @Bean
    public GetClinicProfileQuery getClinicProfileQuery(
            LoadClinicPort loadClinicPort) {
        return new GetClinicProfileService(loadClinicPort);
    }
    @Bean
    public GetCurrentClinicUseCase getCurrentClinicUseCase(
            LoadClinicPort loadClinicPort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort,
            GetClinicRoomsPort getClinicRoomsPort,
            GetDoctorsPort getDoctorsPort,
            LoadAppointmentPort loadAppointmentPort
    ){
        return new GetCurrentClinicService(
                loadClinicPort,
                getAuthenticatedPort,
                loadClinicAdminPort,
                getClinicRoomsPort,
                getDoctorsPort,
                loadAppointmentPort
        );
    }

    @Bean
    public AddPriceToAppointmentTypeUseCase addPriceToAppointmentTypeUseCase(
            SaveClinicPort saveClinicPort,
            GetAuthenticatedPort getAuthenticatedPort,
            LoadClinicAdminPort loadClinicAdminPort,
            LoadAppointmentTypePort loadAppointmentTypePort
    ) {
        return new AddPriceService(
                getAuthenticatedPort,
                loadClinicAdminPort,
                saveClinicPort,
                loadAppointmentTypePort
        );
    }

    @Bean
    public GetDiagnosisQuery getDiagnosisQuery(GetDiagnosisPort getDiagnosisPort) {
        return new GetDiagnosisService(getDiagnosisPort);
    }

    @Bean
    public GetDrugsQuery getDrugsQuery(GetDrugsPort getDrugsPort) {
        return new GetDrugsService(getDrugsPort);
    }

    @Bean
    public AddFinishedAppointmentUseCase addFinishedAppointmentUseCase(
            LoadAppointmentPort loadAppointmentPort,
            GetDiagnosisByIdPort getDiagnosisByIdPort,
            SaveFinishedAppointmentPort saveFinishedAppointmentPort,
            GetDrugByIdPort getDrugByIdPort,
            SavePrescriptionPort savePrescriptionPort,
            DeleteAppointmentScheduleItemPort deleteAppointmentScheduleItemPort,
            LoadClinicReviewPort loadClinicReviewPort,
            LoadDoctorReviewPort loadDoctorReviewPort,
            SaveClinicReviewPort saveClinicReviewPort,
            SaveDoctorReviewPort saveDoctorReviewPort) {
        return new AddFinishedAppointmentService(
                loadAppointmentPort,
                getDiagnosisByIdPort,
                saveFinishedAppointmentPort,
                getDrugByIdPort,
                savePrescriptionPort,
                deleteAppointmentScheduleItemPort,
                loadClinicReviewPort,
                loadDoctorReviewPort,
                saveClinicReviewPort,
                saveDoctorReviewPort);
    }

    @Bean
    public GetFinishedAppointmentProfitUseCase getFinishedAppointmentProfitUseCase(
            GetAuthenticatedPort getAuthenticatedPort,
            GetFinishedAppointmentsPort getFinishedAppointmentsPort,
            LoadClinicAdminPort loadClinicAdminPort,
            LoadClinicPort loadClinicPort
    ) {
       return new GetFinishedAppointmentProfitService(
               getAuthenticatedPort,
               getFinishedAppointmentsPort,
               loadClinicAdminPort,
               loadClinicPort
       );
    }

    @Bean
    public GetBloodTypesQuery getBloodTypesQuery() {
        return new GetBloodTypesService();
    }

    @Bean
    public GetPatientMedicalRecordQuery getPatientMedicalRecordQuery(LoadMedicalRecordPort loadMedicalRecordPort) {
        return new GetPatientMedicalRecordService(loadMedicalRecordPort);
    }

    @Bean
    public ChangeMedicalRecordUseCase changeMedicalRecordUseCase(LoadMedicalRecordByIdPort loadMedicalRecordByIdPort,
                                                                 SaveMedicalRecordPort saveMedicalRecordPort) {
        return new ChangeMedicalRecordService(loadMedicalRecordByIdPort, saveMedicalRecordPort);
    }

    @Bean
    public GetAppointmentScheduleItemByAppointmentIdUseCase getAppointmentScheduleItemByDateUseCase(
            LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort loadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort
    ) {
        return new GetMedicalDoctorAppointmentScheduleItemService(
                loadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort
        );
    }

    @Bean
    public ChangeAppointmentTypeUseCase changeAppointmentTypeUseCase(GetAppointmentTypePort getAppointmentTypePort,
                                                                     SaveAppointmentTypePort saveAppointmentTypePort) {
        return new ChangeAppointmentTypeService(getAppointmentTypePort, saveAppointmentTypePort);
    }

    @Bean
    public GetPrescriptionsQuery getPrescriptionsQuery(GetPrescriptionsPort getPrescriptionsPort,
                                                       GetAuthenticatedPort getAuthenticatedPort,
                                                       GetMedicalNurseByAccountIdPort getMedicalNurseByAccountIdPort) {
        return new GetPrescriptionsService(getPrescriptionsPort, getAuthenticatedPort, getMedicalNurseByAccountIdPort);
    }

    @Bean
    public AcceptPrescriptionUseCase acceptPrescriptionUseCase(GetAuthenticatedPort getAuthenticatedPort,
                                                               GetMedicalNurseByAccountIdPort getMedicalNurseByAccountIdPort,
                                                               GetPrescriptionPort getPrescriptionPort,
                                                               SavePrescriptionPort savePrescriptionPort) {
        return new AcceptPrescriptionService(getAuthenticatedPort,
                getMedicalNurseByAccountIdPort,
                getPrescriptionPort,
                savePrescriptionPort);
    }

    @Bean
    public GetPreviousPatientsQuery getPreviousPatientsQuery(GetAuthenticatedPort getAuthenticatedPort,
                                                             GetDoctorByAccountIdPort getDoctorByAccountIdPort,
                                                             GetPreviousPatientsPort getPreviousPatientsPort) {
        return new GetPreviousPatientsService(getAuthenticatedPort, getDoctorByAccountIdPort, getPreviousPatientsPort);
    }

    @Bean
    public GetPatientsFinishedAppointmetsQuery getPatientsFinishedAppointmetsQuery(
            GetPatientsFinishedAppointmentsPort getPatientsFinishedAppointmentsPort) {
        return new GetPatientsFinishedAppointmentsService(getPatientsFinishedAppointmentsPort);
    }

    @Bean
    public ChangeFinishedAppointmentUseCase changeFinishedAppointmentUseCas(GetFinishedAppointmentPort getFinishedAppointmentPort,
                                                                            GetDiagnosisByIdPort getDiagnosisByIdPort,
                                                                            SaveFinishedAppointmentPort saveFinishedAppointmentPort) {
        return new ChangeFinishedAppointmentService(getFinishedAppointmentPort,
                getDiagnosisByIdPort,
                saveFinishedAppointmentPort);
    }

    @Bean
    public AddOperationUseCase addOperationUseCase(GetDoctorsPort getDoctorsPort,
                                                   GetPatientsPort getPatientsPort,
                                                   GetClinicRoomsPort getClinicRoomsPort,
                                                   SaveAppointmentPort saveAppointmentPort,
                                                   SaveMedicalDoctorScheduleItemPort saveMedicalDoctorScheduleItemPort) {
        return new AddOperationService(getDoctorsPort,
                getPatientsPort,
                getClinicRoomsPort,
                saveAppointmentPort,
                saveMedicalDoctorScheduleItemPort);
    }

    @Bean
    public GetRegistrationRequestsQuery getRegistrationRequestsQuery(GetRegistrationRequestsPort getRegistrationRequestsPort) {
        return new GetRegistrationRequestsService(getRegistrationRequestsPort);
    }

    @Bean
    public AcceptRegistrationUseCase acceptRegistrationUseCase(
        LoadRegistrationRequestPort loadRegistrationRequestPort,
        SavePatientPort savePatientPort,
        DeleteRegistrationRequestPort deleteRegistrationRequestPort,
        SendEmailPort sendEmailPort) {
        return new AcceptRegistrationService(
                loadRegistrationRequestPort, savePatientPort, deleteRegistrationRequestPort, sendEmailPort);
    }

    @Bean
    public RejectRegistrationUseCase rejectRegistrationUseCase(
            LoadRegistrationRequestPort loadRegistrationRequestPort,
            DeleteRegistrationRequestPort deleteRegistrationRequestPort,
            SendEmailPort sendEmailPort) {
        return new RejectRegistrationService(loadRegistrationRequestPort, deleteRegistrationRequestPort, sendEmailPort);
    }

    @Bean
    public ActivateAccountUseCase activateAccountUseCase(
            LoadAccountPort loadAccountPort,
            SaveAccountPort saveAccountPort
    ) {
        return new ActivateAccountService(loadAccountPort, saveAccountPort);
    }
}
