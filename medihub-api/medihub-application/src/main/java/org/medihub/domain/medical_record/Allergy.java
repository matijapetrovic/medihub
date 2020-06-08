package org.medihub.domain.medical_record;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
public class Allergy {
    String name;
    Level level;

    public Allergy(
            String name,
            String level) {
        this.name = name;
        this.level = Level.valueOf(level.toUpperCase());
    }

    public Allergy(
            String name,
            Level level) {
        this.name = name;
        this.level = level;
    }

    public String getLevelLabel() {
        return level.label;
    }

    public int getLevelOrdinal() {
        return level.ordinal;
    }

    public enum Level {
        MILD(0, "Mild"), MODERATE(1, "Moderate"), SEVERE(2, "Severe");

        private int ordinal;
        private String label;

        Level(int ordinal, String label) {
            this.ordinal = ordinal;
            this.label = label;
        }

        private static Map<String, Level> labels = new HashMap<>();

        static {
            for (Level level : values())
                labels.put(level.label, level);
        }

        public Level of(String label) {
            return labels.get(label);
        }
    }
}
