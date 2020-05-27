package org.medihub.persistence.predefined_appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="predefined_appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredefinedAppointmentJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName="id", nullable=true)
    private MedicalDoctorJpaEntity medicalDoctor;

    @Column(name= "date")
    private Date date;

    @Column(name = "time")
    private Time start;

    @Column(name = "duration")
    private double duration;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="clinic_room_id", referencedColumnName="id", nullable=true)
    private ClinicRoomJpaEntity clinicRoomJpaEntity;

    @ManyToOne
    @JoinColumn(name="appointment_type_id", referencedColumnName="id", nullable=true)
    private AppointmentTypeJpaEntity appointmentTypeJpaEntity;
}
