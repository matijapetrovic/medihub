package org.medihub.persistence.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue
    Long id;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name="password")
    @NotNull
    private String password;

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

    @Column(name="password_changed")
    private boolean passwordChanged;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="account_authority",
                joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="authority_id", referencedColumnName = "id"))
    private List<AuthorityJpaEntity> authorities;
}
