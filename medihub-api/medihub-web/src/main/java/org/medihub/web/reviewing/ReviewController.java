package org.medihub.web.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.reviewing.AddClinicReviewUseCase;
import org.medihub.application.ports.incoming.reviewing.AddClinicReviewUseCase.AddClinicReviewCommand;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping(value="/review", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReviewController {
    private final AddClinicReviewUseCase addClinicReviewUseCase;

    @PostMapping("/clinic")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    void addClinicReview(@RequestBody AddClinicReviewRequest request) throws ForbiddenException {
        AddClinicReviewCommand command = new AddClinicReviewCommand(request.getAppointmentId(), request.getRating());
        addClinicReviewUseCase.addClinicReview(command);
    }
}
