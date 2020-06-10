package org.medihub.application.ports.incoming.reviewing;

import lombok.Value;

@Value
public class GetClinicsForReviewOutput {
    Long id;
    String name;
}
