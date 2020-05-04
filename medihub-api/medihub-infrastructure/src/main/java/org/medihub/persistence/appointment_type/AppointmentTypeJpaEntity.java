package org.medihub.persistence.appointment_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="appointment_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentTypeJpaEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "price")
    private String name;
}
