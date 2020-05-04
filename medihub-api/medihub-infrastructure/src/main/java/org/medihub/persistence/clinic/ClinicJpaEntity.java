package org.medihub.persistence.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="clinic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="description")
    private String description;
}
