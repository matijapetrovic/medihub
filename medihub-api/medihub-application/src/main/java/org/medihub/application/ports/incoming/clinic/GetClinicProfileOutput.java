package org.medihub.application.ports.incoming.clinic;

import lombok.Value;

import java.util.List;

@Value
public class GetClinicProfileOutput {
    String name;
    String description;
    String address;
    String city;
    String country;
}
