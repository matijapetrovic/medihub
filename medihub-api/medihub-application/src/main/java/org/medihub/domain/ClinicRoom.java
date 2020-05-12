package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class ClinicRoom {
    private Long id;
    private String name;
    private int number;
    private Clinic clinic;
}
