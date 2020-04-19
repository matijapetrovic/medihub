package org.medihub.encoder;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.EncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncoderAdapter implements EncoderPort {
    private final BCryptPasswordEncoder encoder;

    @Override
    public String encode(String message) {
        return encoder.encode(message);
    }
}
