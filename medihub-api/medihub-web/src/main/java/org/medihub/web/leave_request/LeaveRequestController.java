package org.medihub.web.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.AddLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/leave-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeaveRequestController {
    private final AddLeaveRequestUseCase addLeaveRequestUseCase;
    private final GetLeaveRequestPort getLeaveRequestPort;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    List<?> getAll() {
       return getLeaveRequestPort.getAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    void add(@RequestBody AddLeaveRequest addLeaveRequest) {
        AddLeaveRequestUseCase.AddLeaveCommand addLeaveCommand = makeAddLeaveCommand(addLeaveRequest);
        addLeaveRequestUseCase.addLeave(addLeaveCommand);
    }

    private AddLeaveRequestUseCase.AddLeaveCommand makeAddLeaveCommand(AddLeaveRequest addLeaveRequest) {
        return new AddLeaveRequestUseCase.AddLeaveCommand(
                addLeaveRequest.getDates(),
                addLeaveRequest.getType()
        );
    }
}
