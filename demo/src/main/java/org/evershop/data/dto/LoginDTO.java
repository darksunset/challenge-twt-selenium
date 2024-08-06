package org.evershop.data.dto;

import net.datafaker.Faker;

import java.util.Locale;

public class LoginDTO {
    private String fullName;
    private String email;
    private String password;

    public LoginDTO(){}

    public static LoginDTO createUserDTO(){
        Faker faker = new Faker(new Locale("en","US"));
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(faker.internet().safeEmailAddress());
        loginDTO.setFullName(faker.name().fullName());
        loginDTO.setPassword(faker.internet().password(8, 16, true, false, true));
        return loginDTO;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
