package org.medihub.application.ports.incoming.clinic;

import lombok.Value;

@Value
public class GetClinicNamesOutput {
    Long id;
    String name;
}
