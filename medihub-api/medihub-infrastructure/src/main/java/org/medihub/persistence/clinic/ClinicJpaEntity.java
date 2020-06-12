package org.medihub.persistence.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name="clinic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="description")
    private String description;

    @Column(name="rating")
    private BigDecimal rating;

    @Column(name="review_count")
    private Integer reviewCount;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="clinic_appointment_type_mapping",
        joinColumns = {@JoinColumn(name="clinic_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name="appointment_type_id")
    @Column(name="price")
    private Map<AppointmentTypeJpaEntity, BigDecimal> appointmentTypePrices;
}
