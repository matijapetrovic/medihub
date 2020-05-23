package org.medihub.persistence.leave_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.sql.Date;
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

    @ElementCollection
    @Column(name="dates")
    private List<Date> dates;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    private MedicalDoctorJpaEntity doctor;

    @Column(name="schedule_item_type")
    private Integer type;

}
