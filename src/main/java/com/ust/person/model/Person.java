package com.ust.person.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {

    @Id
    @NotNull(message = "id is mandatory")
    private long id;
    @NotNull(message = "firstName is mandatory")
    @Size(max = 250)
    private String firstName;
    @NotNull(message = "lastName is mandatory")
    @Size(max = 250)
    private String lastName;
    @NotNull(message = "dob is mandatory in format dd-MM-yyyy")
    private String dob;
    @NotNull(message = "address is mandatory")
    private Address address;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date creationDate;

    public Person() {
    }

    public Person(long id, String firstName, String lastName, String dob, Address address, Date creationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.creationDate = creationDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", address=" + address +
                ", creationDate=" + creationDate +
                '}';
    }
}
