package org.medihub.persistence.clinicroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

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
