package org.medihub.application.ports.incoming.medical_record;

import lombok.Value;

@Value
public class BloodTypeOutput {
    String name;
    int value;
}
