package org.medihub.persistence.clinic_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="clinic_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoomJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;
}
