package org.medihub.config;

import org.medihub.application.ports.in.TestUseCase;
import org.medihub.application.ports.out.TestPort;
import org.medihub.application.services.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TestUseCase getTestUseCase(TestPort testPort) {
        return new TestService(testPort);
    }
}
