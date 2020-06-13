package org.medihub.application.ports.incoming.leave_request;

public interface DeleteLeaveRequestUseCase {
    void delete(Long id, String reason);
}
