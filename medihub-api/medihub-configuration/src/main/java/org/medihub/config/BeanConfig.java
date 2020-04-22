package org.medihub.config;

import org.medihub.application.ports.incoming.*;
import org.medihub.application.ports.outgoing.*;
import org.medihub.application.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public TestUseCase getTestUseCase(TestPort testPort) {
        return new TestService(testPort);
    }

    @Bean
    public ChangePasswordUseCase changePasswordUseCase(
            LoadAccountPort loadAccountPort,
            EncoderPort encoderPort,
            SaveAccountPort saveAccountPort) {
        return new ChangePasswordService(loadAccountPort, encoderPort, saveAccountPort);
    }

    @Bean
    public UpdateProfileUseCase updateProfileUseCase(
            LoadAccountPort loadAccountPort,
            SaveAccountPort saveAccountPort) {
        return new UpdateProfileService(loadAccountPort, saveAccountPort);
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
    public AddClinicAdminUseCase getAddClinicAdminUseCase(AddClinicAdminPort addClinicAdminPort) {
        return new AddClinicAdminService(addClinicAdminPort);
    }
}
