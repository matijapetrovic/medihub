package org.medihub.application.ports.incoming.medical_record;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllergyDTO {
    String name;
    Integer level;
    String levelLabel;
}
