package org.medihub.web.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddPriceRequest {
    private Long appointmentTypeId;
    private BigDecimal price;
}
