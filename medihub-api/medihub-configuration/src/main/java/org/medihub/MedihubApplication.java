package org.medihub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MedihubApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedihubApplication.class, args);
    }
}
