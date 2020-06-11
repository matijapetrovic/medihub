package org.medihub.application.ports.incoming.operation;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@Value
@Getter
@Setter
public class OperationOutput {
    Long id;
    String name;
    String doctorFullName;
    String clinicName;
    String date;
    List<Long> presentDoctors;
}
