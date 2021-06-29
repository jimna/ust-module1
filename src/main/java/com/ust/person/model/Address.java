package com.ust.person.model;

import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Address {

    
	@Length(max = 250)
    @NotEmpty
    private String addressLine;
    @Length(max = 250)
    @NotEmpty
    private String city;
    @Length(max = 100)
    @NotEmpty
    private String state;
    @NotNull(message = "Pincode is mandatory")
	@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\\\s{0, 1}[0-9]{3}$")
	private int pinCode;

    

    public Address(@Length(max = 250) @NotEmpty String addressLine, @Length(max = 250) @NotEmpty String city,
			@Length(max = 100) @NotEmpty String state,
			@NotNull(message = "pin") @Pattern(regexp = "^[1-9]{1}[0-9]{2}\\\\s{0, 1}[0-9]{3}$") int pinCode) {
		super();
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
    public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [addressLine=" + addressLine + ", city=" + city + ", state=" + state + ", pinCode=" + pinCode
				+ "]";
	}

	
}
