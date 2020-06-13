package org.medihub.web.leave_request;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.leave_request.*;
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
    private final DeleteLeaveRequestUseCase deleteLeaveRequestUseCase;

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

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public void delete(@PathVariable Long id,
                       @RequestBody String message) {
        deleteLeaveRequestUseCase.delete(id, message);
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
