package org.medihub.persistence.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="registration_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", unique=true)
    @NotNull
    private String email;

    @Column(name="password")
    @NotNull
    private String password;

    @Column(name="first_name")
    @NotNull
    private String firstName;

    @Column(name="last_name")
    @NotNull
    private String lastName;

    @Column(name="address")
    @NotNull
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="telephone_number")
    private String telephoneNumber;

    @Column(name="insurance_number", unique = true)
    @NotNull
    private String insuranceNumber;

}
