package org.medihub.application.ports.incoming.medical_record.dto;

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
