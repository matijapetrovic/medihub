package org.medihub.persistence.medical_nurse_schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_nurse.MedicalNurseJpaEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="medical_nurse_leave_schedule_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalNurseLeaveScheduleJpaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @ManyToOne
    @JoinColumn(name="nurse_id", referencedColumnName = "id", nullable = false)
    MedicalNurseJpaEntity nurse;

    @Column(name="start_time")
    Timestamp startTime;

    @Column(name="schedule_item_type")
    Integer type;

    @Column(name="end_date")
    Timestamp endDate;
}
