package com.ust.person.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
	@NotNull(message = "id is mandatory")
	private Integer id;
	@NotEmpty(message = "firstName is mandatory")
	@Size(max = 250)
	private String firstName;
	@NotEmpty(message = "lastName is mandatory")
	@Size(max = 250)
	private String lastName;
	@NotNull(message = "date of birth is mandatory")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private String dateOfBirth;
	@NotNull(message = "address is mandatory")
	@Valid
	private List<Address> address;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
	private String createdDate;

	public Person() {
	}

	

	public Person(@NotNull(message = "id is mandatory") Integer id,
			@NotEmpty(message = "firstName is mandatory") @Size(max = 250) String firstName,
			@NotEmpty(message = "lastName is mandatory") @Size(max = 250) String lastName,
			@NotNull(message = "date of birth is mandatory") String dateOfBirth,
			@NotNull(message = "address is mandatory") @Valid List<Address> address, @NotNull String createdDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.createdDate = createdDate;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}



	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", address=" + address + ", createdDate=" + createdDate + "]";
	}



	
}
