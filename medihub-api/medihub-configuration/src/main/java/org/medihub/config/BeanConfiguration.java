package org.medihub.config;

import org.medihub.application.ports.incoming.LoginUseCase;
import org.medihub.application.ports.incoming.TestUseCase;
import org.medihub.application.ports.outgoing.TestPort;
import org.medihub.application.services.LoginService;
import org.medihub.application.services.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TestUseCase getTestUseCase(TestPort testPort) {
        return new TestService(testPort);
    }

    @Bean
    public LoginUseCase getLoginUseCase() { return new LoginService(); }
}
