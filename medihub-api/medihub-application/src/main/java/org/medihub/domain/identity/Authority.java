package org.medihub.domain.identity;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class Authority {
    String name;

    public static Authority of(String name) {
        return new Authority(name);
    }
}