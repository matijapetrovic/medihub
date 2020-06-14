package org.medihub.web.scheduling;


import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.scheduling.AutomaticSchedulingUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.NotActiveException;

@RequiredArgsConstructor
@Component
@RestController
public class AutomaticSchedulingController {
    private final AutomaticSchedulingUseCase automaticSchedulingUseCase;

    @Scheduled(cron = "${schedule.cron}")
    void runAutomaticScheduling() throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        automaticSchedulingUseCase.runAutomaticScheduleRoom();
    }
}
