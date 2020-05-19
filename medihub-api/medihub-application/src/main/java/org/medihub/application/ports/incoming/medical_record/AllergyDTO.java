package org.medihub.application.ports.incoming.medical_record;

import lombok.Value;

@Value
public class AllergyDTO {
    String name;
    Integer level;
    String levelLabel;
}
