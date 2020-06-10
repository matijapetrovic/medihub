package org.medihub.domain.account;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class Account implements Serializable {
    private Long id;
    private String email;
    private String password;
    private PersonalInfo personalInfo;
    private boolean passwordChanged;
    private List<Authority> authorities;

    public boolean changePassword(String newPassword) {
        if (password.equalsIgnoreCase(newPassword))
            return false;

        password = newPassword;
        passwordChanged = true;
        return true;
    }

    public void updateProfile(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getFirstName() {
        return personalInfo.getFirstName();
    }

    public String getLastName() {
        return personalInfo.getLastName();
    }

    public String getAddress() {
        return personalInfo.getAddress();
    }

    public String getCity() {
        return personalInfo.getCity();
    }

    public String getCountry() {
        return personalInfo.getCountry();
    }

    public String getTelephoneNumber() {
        return personalInfo.getTelephoneNumber();
    }
}
