package com.ust.person.controller;

import com.google.gson.Gson;
import com.ust.person.model.Address;
import com.ust.person.model.Person;
import com.ust.person.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private PersonService service;

    @InjectMocks
    private PersonController controller;

    private Person person;
    String dob;
    List<Address> address = new ArrayList<Address>();

    public void setAddress(List<Address> address) {
        address.add(new Address("MG Road", "Banglore", "Karnataka", 123456));
    }

    @BeforeEach
    public void setUp() {
        person = new Person(11, "Albert", "shibu", "12-12-1998", address, "12-12-21");

    }

    @Test
    void deleteDataTest() throws Exception {
        when(service.deletePerson(person.getId())).thenReturn(true);
        mock.perform(MockMvcRequestBuilders.put("/api/person/manipulate/11/delete").contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(person))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateDataTest() throws Exception {
        when(service.updatePerson(person.getId(), person)).thenReturn(person);
        mock.perform(MockMvcRequestBuilders.put("/api/person/manipulate/11/update").contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(person))).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deleteDataFailureTest() throws Exception {
        when(service.deletePerson(person.getId())).thenReturn(false);
        mock.perform(MockMvcRequestBuilders.put("/api/person/manipulate/11/delete").contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(person))).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}







































//    @Test
//    void addPersonTest() throws Exception {
//        //person=new Person(11,"Albert","shibu", "12-12-1998",address,"12-12-21");
//        when(service.addPerson(person)).thenReturn("Person Already Exist");
//        mock.perform(MockMvcRequestBuilders.post("/api/person/add").contentType(MediaType.APPLICATION_JSON)
//                .content(new Gson().toJson(person))).andExpect(MockMvcResultMatchers.status().isCreated());
//
//    }