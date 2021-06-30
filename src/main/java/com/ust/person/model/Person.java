package com.ust.person.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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
	@JsonFormat(pattern = "dd-mm-yyyy")
	private Date dateOfBirth;
	@NotNull(message = "address is mandatory")
	private List<Address> address;
	@NotNull
	@JsonFormat(pattern = "dd-mm-yyyy")
	private Date creationDate;

	public Person() {
	}

	public Person(@NotNull(message = "id is mandatory") Integer id,
			@NotEmpty(message = "firstName is mandatory") @Size(max = 250) String firstName,
			@NotEmpty(message = "lastName is mandatory") @Size(max = 250) String lastName,
			@NotNull(message = "date of birth is mandatory") Date dateOfBirth,
			@NotNull(message = "address is mandatory") List<Address> address, @NotNull Date creationDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.creationDate = creationDate;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", address=" + address + ", creationDate=" + creationDate + "]";
	}

}
