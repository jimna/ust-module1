package com.ust.person.model;



import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class Person {


    @NotNull(message = "ID is mandatory")
    private Integer id;
    @NotEmpty(message = "FirstName is mandatory")
    @Size(max = 250)
    private String firstName;
    @NotEmpty(message = "LastName is mandatory")
    @Size(max = 250)
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    private String dateOfBirth;
    @Valid
    private List<Address> address;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yy")
    private String createdDate;

    public Person() {
    }

    public Person(Integer id, String firstName, String lastName, String dateOfBirth, List<Address> address, String createdDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth=dateOfBirth;
        this.createdDate=createdDate;

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address=" + address +
                ", createdDate=" + createdDate +
                '}';
    }
}
