package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;

@Value
public class Cnt {
    String interval;
    Integer count;
}
