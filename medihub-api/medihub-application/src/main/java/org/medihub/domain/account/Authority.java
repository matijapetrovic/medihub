package org.medihub.domain.account;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class Authority {
    Long id;
    String name;
}