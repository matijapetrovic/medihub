package org.medihub.web.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateClinicRequest {
    String name;
    String addressLine;
    String city;
    String country;
    String description;
}
