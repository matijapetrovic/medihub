package org.medihub.persistence.leave_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_nurse.MedicalNurseJpaEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="nurse_leave_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NurseLeaveRequestJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="nurse_id", referencedColumnName = "id")
    private MedicalNurseJpaEntity nurse;

    @Column(name="schedule_item_type")
    private Integer type;
}
