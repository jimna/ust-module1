package com.ust.person.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


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
    @Min(value = 100000,message ="Pincode  must contain minimum 6 digits" )
    @Max(value = 999999,message ="Pincode must contain maximum 6 digits" )
//	@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\\\s{0, 1}[0-9]{3}$")
	private int pincode;

	public Address(@Length(max = 250) @NotEmpty String addressLine, @Length(max = 250) @NotEmpty String city,
			@Length(max = 100) @NotEmpty String state,
			@NotNull(message = "Pincode is mandatory") @Min(value = 100000, message = "Pincode  must contain minimum 6 digits") @Max(value = 999999, message = "Pincode must contain maximum 6 digits") int pincode) {
		super();
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
		return "Address [addressLine=" + addressLine + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ "]";
	}

	

	
}
