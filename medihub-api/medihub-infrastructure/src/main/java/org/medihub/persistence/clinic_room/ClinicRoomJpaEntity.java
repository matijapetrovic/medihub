package org.medihub.persistence.clinic_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.clinic.ClinicJpaEntity;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="clinic_room", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"clinic_id", "number"})
})
public class ClinicRoomJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number")
    private int number;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name="clinic_id", nullable = false)
    private ClinicJpaEntity clinic;
}
