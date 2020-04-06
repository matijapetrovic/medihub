package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.in.TestUseCase;
import org.medihub.application.ports.out.TestPort;

@RequiredArgsConstructor
public class TestService implements TestUseCase {
    private final TestPort testPort;

    @Override
    public String doTest() {
        return testPort.loadTest().test();
    }
}
