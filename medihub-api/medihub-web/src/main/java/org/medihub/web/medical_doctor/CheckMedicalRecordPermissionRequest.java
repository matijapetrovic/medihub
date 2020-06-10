package org.medihub.web.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckMedicalRecordPermissionRequest {
    private Long doctorId;
    private Long patientId;
}
