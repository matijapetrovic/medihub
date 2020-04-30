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
    @GeneratedValue
    Long id;

    @Column(name = "name")
    private String name;
}
