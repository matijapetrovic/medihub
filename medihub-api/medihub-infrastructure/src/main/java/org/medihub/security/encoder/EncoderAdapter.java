package org.medihub.security.encoder;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.encoding.EncoderPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncoderAdapter implements EncoderPort {
    private final PasswordEncoder encoder;

    @Override
    public String encode(String message) {
        return encoder.encode(message);
    }
}
