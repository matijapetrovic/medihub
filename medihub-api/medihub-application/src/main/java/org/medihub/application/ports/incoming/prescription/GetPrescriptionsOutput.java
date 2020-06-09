package org.medihub.application.ports.incoming.prescription;

import lombok.Value;

@Value
public class GetPrescriptionsOutput {
    Long id;
    String drug;
    String diagnosis;
}
