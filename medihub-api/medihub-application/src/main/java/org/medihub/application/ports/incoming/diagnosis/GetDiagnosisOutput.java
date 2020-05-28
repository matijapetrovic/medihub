package org.medihub.application.ports.incoming.diagnosis;

import lombok.Value;

@Value
public class GetDiagnosisOutput {
    Long id;
    String name;
}
