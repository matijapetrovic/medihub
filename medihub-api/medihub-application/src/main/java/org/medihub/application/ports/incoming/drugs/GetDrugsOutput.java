package org.medihub.application.ports.incoming.drugs;

import lombok.Value;

@Value
public class GetDrugsOutput {
    Long id;
    String name;
}
