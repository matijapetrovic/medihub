package org.medihub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) // TODO delete when database is added
public class MedihubApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedihubApplication.class, args);
    }
}
