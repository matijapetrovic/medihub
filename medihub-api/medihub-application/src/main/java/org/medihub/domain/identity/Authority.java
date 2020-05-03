package org.medihub.domain.identity;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class Authority {
    Long id;
    String name;
}