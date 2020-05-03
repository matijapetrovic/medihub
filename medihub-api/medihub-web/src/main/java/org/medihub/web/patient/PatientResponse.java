package org.medihub.web.patient;

public class PatientResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String addressLine;

    public PatientResponse
            (
             String email,
             String firstName,
             String lastName,
             String addressLine){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine = addressLine;
    }
}
