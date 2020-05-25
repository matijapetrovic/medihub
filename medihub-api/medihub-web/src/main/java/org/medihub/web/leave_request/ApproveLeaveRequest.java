package org.medihub.web.leave_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveLeaveRequest {
    Long id;
    Long medicalDoctorId;
}
