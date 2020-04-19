package org.medihub.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class Authority {
    Long id;
    String name;
}