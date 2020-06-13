package org.medihub.web.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.AddLeaveRequestUseCase;
import org.medihub.application.ports.incoming.leave_request.ApproveLeaveRequestUseCase;
import org.medihub.application.ports.incoming.leave_request.GetLeaveRequestUseCase;
import org.medihub.application.ports.outgoing.leave_request.DeleteLeaveRequestPort;
import org.medihub.application.ports.outgoing.leave_request.GetLeaveRequestPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/leave-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeaveRequestController {
    private final AddLeaveRequestUseCase addLeaveRequestUseCase;
    private final ApproveLeaveRequestUseCase approveLeaveRequestUseCase;

    private final GetLeaveRequestUseCase getLeaveRequestUseCase;
    private final DeleteLeaveRequestPort deleteLeaveRequestPort;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public ResponseEntity<List<?>> getAll() {
       return ResponseEntity.ok(getLeaveRequestUseCase.getAll());
    }

    // TODO : MAKE QUERY NOT PORT!!!
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public void add(@RequestBody AddLeaveRequest addLeaveRequest) {
        AddLeaveRequestUseCase.AddLeaveCommand addLeaveCommand = makeAddLeaveCommand(addLeaveRequest);
        addLeaveRequestUseCase.addLeave(addLeaveCommand);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public void delete(@RequestBody Long id) {
        deleteLeaveRequestPort.delete(id);
    }

    @PostMapping("/approve")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public void approveLeaveRequest(@RequestBody ApproveLeaveRequest approveLeaveRequest) {
        approveLeaveRequestUseCase.approveLeaveRequest(approveLeaveRequest.getId(), approveLeaveRequest.getMedicalDoctorId());
    }

    private AddLeaveRequestUseCase.AddLeaveCommand makeAddLeaveCommand(AddLeaveRequest addLeaveRequest) {
        return new AddLeaveRequestUseCase.AddLeaveCommand(
                addLeaveRequest.getDates(),
                addLeaveRequest.getType()
        );
    }
}
