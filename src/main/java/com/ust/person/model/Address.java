package com.ust.person.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;


public class Address {

    @Length(max = 250)
    @NotEmpty(message = "AddressLine is mandatory")
    private String addressLine;
    @Length(max = 250)
    @NotEmpty(message = "City is mandatory")
    private String city;
    @Length(max = 100)
    @NotEmpty(message = "State is mandatory")
    private String state;
    @Min(6)
    @Max(6)
    @NotNull(message = "Pincode is mandatory and must contain 6 digits")
    private int pincode;

    public Address(String addressLine, String city, String state, int pincode) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
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

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
