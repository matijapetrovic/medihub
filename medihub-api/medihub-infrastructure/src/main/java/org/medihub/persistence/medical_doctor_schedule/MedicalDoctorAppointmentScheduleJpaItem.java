package org.medihub.persistence.medical_doctor_schedule;

import lombok.Getter;
import lombok.Setter;
import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.*;

@Entity
@Table(name="medical_doctor_appointment_schedule_item")
@Getter
@Setter
public class MedicalDoctorAppointmentScheduleJpaItem extends  MedicalDoctorScheduleItemJpaEntity {

    @OneToOne
    @JoinColumn(name="appointment_id", referencedColumnName = "id", nullable = false)
    AppointmentJpaEntity appointment;
}
