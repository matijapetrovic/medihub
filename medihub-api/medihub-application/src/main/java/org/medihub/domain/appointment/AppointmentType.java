package org.medihub.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
public class AppointmentType {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

}
