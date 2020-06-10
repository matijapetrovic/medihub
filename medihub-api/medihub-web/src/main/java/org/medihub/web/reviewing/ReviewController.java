package org.medihub.web.reviewing;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.reviewing.*;
import org.medihub.application.ports.incoming.reviewing.AddClinicReviewUseCase.AddClinicReviewCommand;
import org.medihub.application.ports.incoming.reviewing.AddDoctorReviewUseCase.AddDoctorReviewCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping(value="/review", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReviewController {
    private final AddClinicReviewUseCase addClinicReviewUseCase;
    private final AddDoctorReviewUseCase addDoctorReviewUseCase;
    private final GetClinicsForReviewQuery getClinicsForReviewQuery;
    private final GetDoctorsForReviewQuery getDoctorsForReviewQuery;

    @PostMapping("/clinic")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    void addClinicReview(@RequestBody AddReviewRequest request) throws ForbiddenException {
        AddClinicReviewCommand command = new AddClinicReviewCommand(request.getId(), request.getRating());
        addClinicReviewUseCase.addClinicReview(command);
    }

    @PostMapping("/doctor")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    void addDoctorReview(@RequestBody AddReviewRequest request) throws ForbiddenException {
        AddDoctorReviewCommand command = new AddDoctorReviewCommand(request.getId(), request.getRating());
        addDoctorReviewUseCase.addDoctorReview(command);
    }

    @GetMapping("/clinic")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    ResponseEntity<List<GetClinicsForReviewOutput>> getClinicsForReview() {
        return ResponseEntity.ok(getClinicsForReviewQuery.getClinicsForReview());
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    ResponseEntity<List<GetDoctorsForReviewOutput>> getDoctorsForReview() {
        return ResponseEntity.ok(getDoctorsForReviewQuery.getDoctorsForReview());
    }

}
