package org.medihub.web;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.in.TestUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestUseCase testUseCase;

    @GetMapping(path = "/")
    String hello() {
        return testUseCase.doTest();
    }
}
