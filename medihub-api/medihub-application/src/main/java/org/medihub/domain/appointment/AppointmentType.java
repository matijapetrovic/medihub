package org.medihub.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppointmentType {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
}
