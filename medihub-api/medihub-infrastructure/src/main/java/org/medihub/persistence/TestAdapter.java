package org.medihub.persistence;

import org.medihub.application.ports.outgoing.TestPort;
import org.medihub.domain.Test;
import org.springframework.stereotype.Component;

@Component
public class TestAdapter implements TestPort {
    @Override
    public Test loadTest() {
        return new Test("Hello World from API");
    }
}
