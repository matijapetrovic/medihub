package org.medihub.persistence.working_calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="working_calendar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingCalendarJpaEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
