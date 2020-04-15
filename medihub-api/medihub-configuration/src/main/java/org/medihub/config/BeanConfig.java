package org.medihub.config;

import org.medihub.application.ports.incoming.GetAccountQuery;
import org.medihub.application.ports.incoming.LoginUseCase;
import org.medihub.application.ports.incoming.SaveAccountUseCase;
import org.medihub.application.ports.incoming.TestUseCase;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.application.ports.outgoing.SaveAccountPort;
import org.medihub.application.ports.outgoing.TestPort;
import org.medihub.application.services.GetAccountService;
import org.medihub.application.services.LoginService;
import org.medihub.application.services.SaveAccountService;
import org.medihub.application.services.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public TestUseCase getTestUseCase(TestPort testPort) {
        return new TestService(testPort);
    }

    @Bean
    public LoginUseCase getLoginUseCase(LoadAccountPort loadAccountPort) { return new LoginService(loadAccountPort); }

    @Bean
    public GetAccountQuery getAccountQuery(LoadAccountPort loadAccountPort) {
        return new GetAccountService(loadAccountPort);
    }

    @Bean
    public SaveAccountUseCase saveAccountUseCase(SaveAccountPort saveAccountPort) {
        return new SaveAccountService(saveAccountPort);
    }
}
