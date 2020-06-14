package org.medihub.persistence.personal_info;

import lombok.*;
import org.medihub.persistence.account.AccountJpaEntity;

import javax.persistence.*;

@Entity
@Table(name="personal_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalInfoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="telephone_number")
    private String telephoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private AccountJpaEntity account;
}
