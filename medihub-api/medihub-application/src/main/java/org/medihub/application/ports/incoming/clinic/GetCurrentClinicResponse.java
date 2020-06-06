package org.medihub.application.ports.incoming.clinic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medihub.domain.Money;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetCurrentClinicResponse {
    Long id;
    String name;
    String addressLine;
    String city;
    String country;
    String description;
    Map<AppointmentType, Money> appointmentPrices;
    List<ClinicAdmin> admins;
    List<ClinicRoom> clinicRooms;
    List<MedicalDoctor> medicalDoctors;
    List<Appointment> appointments;
    BigDecimal rating;
    Integer count;
}
