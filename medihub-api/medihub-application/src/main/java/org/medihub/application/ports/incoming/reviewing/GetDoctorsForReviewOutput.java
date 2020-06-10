package org.medihub.application.ports.incoming.reviewing;

import lombok.Value;

@Value
public class GetDoctorsForReviewOutput {
    Long id;
    String name;
}
