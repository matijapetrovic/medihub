package org.medihub.web;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicAdminUseCase;
import org.medihub.application.ports.incoming.TestUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {
    private final TestUseCase testUseCase;
    private final AddClinicAdminUseCase addClinicAdminUseCase;

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        String response = testUseCase.doTest();
        System.out.println("Response is: " + response);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/clinicAdmin/add")
    String addClinicAdmin() { return addClinicAdminUseCase.addClinicAdmin(); }
}
