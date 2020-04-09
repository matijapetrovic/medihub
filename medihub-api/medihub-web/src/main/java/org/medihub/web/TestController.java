package org.medihub.web;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicAdminUseCase;
import org.medihub.application.ports.incoming.TestUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestUseCase testUseCase;
    private final AddClinicAdminUseCase addClinicAdminUseCase;

    @GetMapping(path = "/")
    String hello() {
        return testUseCase.doTest();
    }

    @GetMapping(path = "/clinicAdmin/add")
    String addClinicAdmin() { return addClinicAdminUseCase.addClinicAdmin(); }
}
