package org.medihub.web.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper =  false)
@AllArgsConstructor
public class ApiAuthenticationError  extends ApiSubError{

}
