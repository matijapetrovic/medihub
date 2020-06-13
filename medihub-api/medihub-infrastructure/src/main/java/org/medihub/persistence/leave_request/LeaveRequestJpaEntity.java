package org.medihub.persistence.leave_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="leave_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    private MedicalDoctorJpaEntity doctor;

    @Column(name="schedule_item_type")
    private Integer type;

}
