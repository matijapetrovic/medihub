package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.TestUseCase;
import org.medihub.application.ports.outgoing.TestPort;

@RequiredArgsConstructor
public class TestService implements TestUseCase {
    private final TestPort testPort;

    @Override
    public String doTest() {
        return testPort.loadTest().test();
    }
}
