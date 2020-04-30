package org.medihub.web.clinic_admin;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicAdminUseCase;
import org.medihub.domain.ClinicAdmin;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clinicAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClinicAdminController {

    private final AddClinicAdminUseCase addClinicAdminUseCase;

    @PostMapping(path = "/add")
    String addClinicAdmin() {
       return null;
    }
}
