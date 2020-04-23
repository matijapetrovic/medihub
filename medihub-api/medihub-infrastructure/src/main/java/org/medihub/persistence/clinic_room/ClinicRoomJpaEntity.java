package org.medihub.persistence.clinic_room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clinicRooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoomJpaEntity {
    @Id
    @Column(name = "name")
    private String name;
}
