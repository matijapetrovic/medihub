package org.medihub.persistence;

import org.medihub.application.ports.outgoing.AddClinicAdminPort;
import org.springframework.stereotype.Component;

@Component
public class AddClinicAdminAdapter implements AddClinicAdminPort {
    @Override
    public String addClinicAdmin() {
        return "Admin added";
    }
}
