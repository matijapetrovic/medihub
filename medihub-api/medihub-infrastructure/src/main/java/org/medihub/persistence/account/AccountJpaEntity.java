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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", unique=true, nullable = false)
    @NotNull
    private String email;

    @Column(name="password", nullable = false)
    @NotNull
    private String password;

    @Column(name="password_changed")
    private boolean passwordChanged;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="account_authority",
                joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="authority_id", referencedColumnName = "id"))
    private List<AuthorityJpaEntity> authorities;
}
