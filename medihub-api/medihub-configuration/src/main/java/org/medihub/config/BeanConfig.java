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
    public RegisterPatientUseCase registerPatientUseCase(SaveRegistrationRequestPort saveRegistrationRequestPort) {
        return new RegisterPatientService(saveRegistrationRequestPort);
    }

    @Bean
    public GetAccountQuery getAccountQuery(LoadAccountPort loadAccountPort) {
        return new GetAccountService(loadAccountPort);
    }

    @Bean
    public SaveAccountUseCase saveAccountUseCase(SaveAccountPort saveAccountPort) {
        return new SaveAccountService(saveAccountPort);
    }

    @Bean
    public AddClinicAdminUseCase getAddClinicAdminUseCase(AddClinicAdminPort addClinicAdminPort) {
        return new AddClinicAdminService(addClinicAdminPort);
    }
}
