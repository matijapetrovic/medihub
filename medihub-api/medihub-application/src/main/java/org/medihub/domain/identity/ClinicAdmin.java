package org.medihub.domain.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClinicAdmin extends  User {
    private Clinic clinic;
}
