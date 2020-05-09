package org.medihub.application.ports.incoming.authentication;

import lombok.Value;

import java.util.List;

@Value
public class LoginOutput {
    String email;
    boolean passwordChanged;
    List<String> roles;
    Long clinicId;
}
