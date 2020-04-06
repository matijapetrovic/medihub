package org.medihub.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Test {
    private String message;

    public String test() {
        return message;
    }
}
