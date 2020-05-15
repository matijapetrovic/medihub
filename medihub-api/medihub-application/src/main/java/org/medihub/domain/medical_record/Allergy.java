package org.medihub.domain.medical_record;

import lombok.Value;

@Value
public class Allergy {
    String name;
    Level level;

    public Allergy(
            String name,
            String level) {
        this.name = name;
        this.level = Level.valueOf(level);
    }

    public String getLevelString() {
        return level.toString();
    }

    private enum Level {
        MILD, MODERATE, SEVERE;
    }
}
