package com.ust.person.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;


public class Address {

    @Size(max = 250)
    @NotEmpty(message = "AddressLine is mandatory")
    private String addressLine;
    @Size(max = 250)
    @NotEmpty(message = "City is mandatory")
    private String city;
    @Size(max = 100)
    @NotEmpty(message = "State is mandatory")
    private String state;
    @NotNull(message = "Pincode is mandatory and must contain 6 digits")
    @Min(value = 100000,message ="Pincode  must contain minimum 6 digits" )
    @Max(value = 999999,message ="Pincode must contain maximum 6 digits" )
    private Integer pinCode;

    public Address(String addressLine, String city, String state, Integer pinCode) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public Address() {
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
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

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }
}
