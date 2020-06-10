package org.medihub.domain.account;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

@Value
@EqualsAndHashCode(callSuper = false)
public class Authority implements Serializable {
    Long id;
    String name;
}