package org.medihub.config;

import org.medihub.application.ports.incoming.*;
import org.medihub.application.ports.outgoing.AddClinicAdminPort;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.application.ports.outgoing.SaveAccountPort;
import org.medihub.application.ports.outgoing.TestPort;
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
