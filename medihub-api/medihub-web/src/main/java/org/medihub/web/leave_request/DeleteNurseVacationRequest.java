package org.medihub.web.leave_request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteNurseVacationRequest {
    Long id;
    String rejectReason;
}
