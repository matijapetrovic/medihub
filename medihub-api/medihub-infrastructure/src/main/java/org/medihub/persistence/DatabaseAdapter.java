package org.medihub.persistence;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.DatabasePort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequiredArgsConstructor
public class DatabaseAdapter implements DatabasePort {
    @Override
    public void addClinicRoom(String clinicRoom) { } // add to instantiated database
}
