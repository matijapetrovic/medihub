package org.medihub.persistence;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.TestPort;
import org.medihub.domain.Test;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class TestAdapter implements TestPort {
    private final TestRepository repository;
    @Override

    public Test loadTest() {
        TestEntity testEntity = repository.findById(1L)
                .orElseThrow(EntityNotFoundException::new);
        return new Test(testEntity.getMessage());
    }
}
