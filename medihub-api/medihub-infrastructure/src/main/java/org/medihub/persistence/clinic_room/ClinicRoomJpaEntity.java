package org.medihub.persistence.clinic_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic.ClinicJpaEntity;

import javax.persistence.*;

@Entity
@Table(name="clinic_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoomJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", unique = true)
    private int number;

    @ManyToOne
    @JoinColumn(name="clinic_id", nullable = false)
    private ClinicJpaEntity clinic;
}
