package org.medihub.web.clinic_admin;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.clinic_admin.AddClinicAdminUseCase.AddClinicAdminCommand;
import org.medihub.application.ports.incoming.clinic_admin.AddClinicAdminUseCase;
import org.medihub.domain.clinic.ClinicAdmin;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clinicAdmin", produces = MediaType.APPLICATION_JSON_VALUE)

public class ClinicAdminController {

    private final AddClinicAdminUseCase addClinicAdminUseCase;

    @PostMapping(path = "/add")
    @PreAuthorize("hasRole('ROLE_CLINIC_CENTER_ADMIN')")
    ClinicAdmin addClinicAdmin(@RequestBody AddClinicAdminRequest addRequest) throws AccountNotFoundException {
       AddClinicAdminCommand command = createCommand(addRequest);
       ClinicAdmin clinicAdmin = addClinicAdminUseCase.addClinicAdmin(command);

       return clinicAdmin;
    }

    private AddClinicAdminCommand createCommand(AddClinicAdminRequest addRequest) {
        return new AddClinicAdminCommand(
                addRequest.getEmail(),
                addRequest.getPassword(),
                addRequest.getFirstName(),
                addRequest.getLastName(),
                addRequest.getAddress(),
                addRequest.getCity(),
                addRequest.getCountry(),
                addRequest.getTelephoneNum(),
                addRequest.getClinic()
        );
    }

}
