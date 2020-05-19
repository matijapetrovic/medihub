package org.medihub.persistence.medical_doctor_schedule;

import lombok.Getter;
import lombok.Setter;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.patient.PatientJpaEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="medical_doctor_appointment_schedule_item")
@Getter
@Setter
public class MedicalDoctorAppointmentScheduleJpaItem extends  MedicalDoctorScheduleItemJpaEntity {

    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "id", nullable = false)
    PatientJpaEntity patient;

    @ManyToOne
    @JoinColumn(name="clinic_room_id", referencedColumnName = "id", nullable = false)
    ClinicRoomJpaEntity clinicRoom;
}
