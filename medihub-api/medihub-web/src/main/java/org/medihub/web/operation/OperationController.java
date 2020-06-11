package org.medihub.web.operation;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.operation.AddOperationUseCase;
import org.medihub.application.ports.incoming.operation.OperationOutput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/operation", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationController {
    private final AddOperationUseCase addOperationUseCase;

    @PostMapping("/add")
    ResponseEntity<OperationOutput> addOperation(@RequestBody AddOperationRequest request) {
        AddOperationUseCase.AddOperationCommand command = createAddCommand(request);

        return ResponseEntity.ok(addOperationUseCase.addOperation(command));
    }

    private AddOperationUseCase.AddOperationCommand createAddCommand(AddOperationRequest request) {
        return new AddOperationUseCase.AddOperationCommand(
                request.getDate(),
                request.getTime(),
                request.getPatientId(),
                request.getDoctorId(),
                request.getClinicRoomId(),
                request.getPresentDoctors()
        );
    }
}
