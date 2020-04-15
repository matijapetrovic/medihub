package org.medihub.persistence.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue
    Long id;

    @Column(name="name")
    @NotNull
    private String name;
}
