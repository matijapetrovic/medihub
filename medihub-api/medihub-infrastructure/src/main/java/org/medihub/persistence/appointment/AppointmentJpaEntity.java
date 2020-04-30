package org.medihub.persistence.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentJpaEntity {
    @Id
    @GeneratedValue
    Long id;
}
