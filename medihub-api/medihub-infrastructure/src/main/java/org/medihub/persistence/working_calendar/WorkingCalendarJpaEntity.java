package org.medihub.persistence.working_calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="working_calendar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingCalendarJpaEntity {
    @Id
    @GeneratedValue
    Long id;
}
