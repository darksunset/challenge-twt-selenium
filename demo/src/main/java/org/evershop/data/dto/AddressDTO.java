package org.evershop.data.dto;

import net.datafaker.Faker;

import java.util.Locale;

public class AddressDTO {
    private String fullName;
    private String address1;
    private String postCode;
    private String city;
    private String state;
    private String country;
    private String phone;

    public AddressDTO() {
    }

    public static AddressDTO createAddressDTO() {
        Locale locale = new Locale("en", "US");
        Faker faker = new Faker(locale);
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setFullName(faker.name().fullName());
        addressDTO.setAddress1(faker.address().streetAddress());
        addressDTO.setPostCode(faker.address().postcode());
        addressDTO.setCity(faker.address().cityName());
        addressDTO.setState(faker.address().state());
        addressDTO.setCountry(locale.getDisplayCountry());
        addressDTO.setPhone(faker.phoneNumber().phoneNumberNational());

        return addressDTO;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
